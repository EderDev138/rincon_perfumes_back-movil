package cl.perfumes.rincon_perfumes_back.service.impl;

import cl.perfumes.rincon_perfumes_back.dto.response.QrResponse;
import cl.perfumes.rincon_perfumes_back.exception.ResourceNotFoundException;
import cl.perfumes.rincon_perfumes_back.model.entidades.ClienteEntidad;
import cl.perfumes.rincon_perfumes_back.model.entidades.PedidoEntidad;
import cl.perfumes.rincon_perfumes_back.repository.PedidoRepositorio;
import cl.perfumes.rincon_perfumes_back.service.EmailServicio;
import cl.perfumes.rincon_perfumes_back.service.QrServicio;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class QrServicioImplementacion implements QrServicio {

    @Autowired
    private PedidoRepositorio pedidoRepositorio;
    
    @Autowired
    private EmailServicio emailServicio;

    @Value("${qr.width:300}")
    private int qrWidth;

    @Value("${qr.height:300}")
    private int qrHeight;

    @Override
    @Transactional
    public PedidoEntidad generarQrParaPedido(PedidoEntidad pedido) {
        // Generar código único de retiro
        String codigoRetiro = generarCodigoRetiro(pedido.getId());
        pedido.setCodigoRetiro(codigoRetiro);
        
        // Crear contenido del QR (JSON con datos del pedido)
        String contenidoQr = crearContenidoQr(pedido, codigoRetiro);
        
        // Generar imagen QR en Base64
        String qrBase64 = generarQrBase64(contenidoQr);
        pedido.setQrCodeBase64(qrBase64);
        
        // Guardar pedido actualizado
        return pedidoRepositorio.save(pedido);
    }

    @Override
    public QrResponse obtenerQrPorPedido(Long pedidoId) {
        PedidoEntidad pedido = pedidoRepositorio.findById(pedidoId)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado: " + pedidoId));
        
        return construirQrResponse(pedido);
    }

    @Override
    @Transactional
    public QrResponse validarCodigoRetiro(String codigoRetiro) {
        PedidoEntidad pedido = pedidoRepositorio.findByCodigoRetiro(codigoRetiro)
                .orElseThrow(() -> new ResourceNotFoundException("Código de retiro inválido: " + codigoRetiro));
        
        // Verificar si ya fue retirado
        if (Boolean.TRUE.equals(pedido.getRetirado())) {
            return QrResponse.builder()
                    .pedidoId(pedido.getId())
                    .codigoRetiro(codigoRetiro)
                    .estado(pedido.getEstado())
                    .retirado(true)
                    .fechaPedido(pedido.getFechaPedido())
                    .mensaje(" Este pedido ya fue retirado el " + 
                            pedido.getFechaRetiro().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")))
                    .build();
        }
        
        // Marcar como retirado
        pedido.setRetirado(true);
        pedido.setFechaRetiro(LocalDateTime.now());
        pedido.setEstado("ENTREGADO");
        pedido.setFechaActualizacion(LocalDateTime.now());
        pedidoRepositorio.save(pedido);
        
        return QrResponse.builder()
                .pedidoId(pedido.getId())
                .codigoRetiro(codigoRetiro)
                .estado("ENTREGADO")
                .retirado(true)
                .fechaPedido(pedido.getFechaPedido())
                .nombreCliente(obtenerNombreCliente(pedido))
                .mensaje("✅ Pedido validado correctamente. Entregar al cliente.")
                .build();
    }

    @Override
    public String generarCodigoRetiro(Long pedidoId) {
        // Formato: RP-{ID}-{UUID corto}-{timestamp}
        String uuid = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        String timestamp = String.valueOf(System.currentTimeMillis() % 10000);
        return String.format("RP-%d-%s-%s", pedidoId, uuid, timestamp);
    }

    @Override
    public String generarQrBase64(String contenido) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.MARGIN, 2);
            
            BitMatrix bitMatrix = qrCodeWriter.encode(
                    contenido, 
                    BarcodeFormat.QR_CODE, 
                    qrWidth, 
                    qrHeight, 
                    hints
            );
            
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
            
            return Base64.getEncoder().encodeToString(outputStream.toByteArray());
            
        } catch (WriterException | java.io.IOException e) {
            throw new RuntimeException("Error al generar código QR: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public QrResponse reenviarQr(Long pedidoId) {
        PedidoEntidad pedido = pedidoRepositorio.findById(pedidoId)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado: " + pedidoId));
        
        // Si no tiene QR, generarlo
        if (pedido.getQrCodeBase64() == null || pedido.getCodigoRetiro() == null) {
            pedido = generarQrParaPedido(pedido);
        }
        
        // Enviar por email
        boolean enviado = emailServicio.enviarCorreoQrRetiro(pedido);
        
        if (enviado) {
            pedido.setQrEnviado(true);
            pedidoRepositorio.save(pedido);
        }
        
        return construirQrResponse(pedido);
    }

    /**
     * Crea el contenido JSON que se codificará en el QR
     */
    private String crearContenidoQr(PedidoEntidad pedido, String codigoRetiro) {
        return String.format(
                "{\"tipo\":\"RETIRO_RINCON_PERFUMES\"," +
                "\"pedidoId\":%d," +
                "\"codigo\":\"%s\"," +
                "\"fecha\":\"%s\"," +
                "\"total\":%.2f}",
                pedido.getId(),
                codigoRetiro,
                pedido.getFechaPedido().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                pedido.getTotal().doubleValue()
        );
    }

    /**
     * Construye el objeto QrResponse completo
     */
    private QrResponse construirQrResponse(PedidoEntidad pedido) {
        ClienteEntidad cliente = pedido.getCliente();
        String telefono = cliente.getTelefono();
        String correo = cliente.getUsuario().getCorreo();
        
        // Generar URL de WhatsApp
        String mensajeWhatsApp = String.format(
                "🛍️ *Rincón Perfumes*\n\n" +
                "¡Hola %s!\n\n" +
                "Tu pedido #%d está listo para retiro.\n" +
                "Código: *%s*\n\n" +
                "Presenta este mensaje o el QR en tienda.\n\n" +
                "¡Gracias por tu compra! 💐",
                cliente.getPrimerNombre(),
                pedido.getId(),
                pedido.getCodigoRetiro()
        );
        
        String whatsappUrl = null;
        if (telefono != null && !telefono.isEmpty()) {
            whatsappUrl = emailServicio.generarUrlWhatsApp(telefono, mensajeWhatsApp);
        }
        
        return QrResponse.builder()
                .pedidoId(pedido.getId())
                .codigoRetiro(pedido.getCodigoRetiro())
                .qrCodeBase64(pedido.getQrCodeBase64())
                .estado(pedido.getEstado())
                .fechaPedido(pedido.getFechaPedido())
                .nombreCliente(obtenerNombreCliente(pedido))
                .correoCliente(correo)
                .telefonoCliente(telefono)
                .enviado(pedido.getQrEnviado())
                .retirado(pedido.getRetirado())
                .whatsappUrl(whatsappUrl)
                .mensaje("QR generado correctamente")
                .build();
    }

    /**
     * Obtiene el nombre completo del cliente
     */
    private String obtenerNombreCliente(PedidoEntidad pedido) {
        ClienteEntidad cliente = pedido.getCliente();
        if (cliente != null) {
            return cliente.getPrimerNombre() + " " + cliente.getPrimerApellido();
        }
        return "Cliente";
    }
}
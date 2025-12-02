package cl.perfumes.rincon_perfumes_back.controller;

import cl.perfumes.rincon_perfumes_back.dto.response.QrResponse;
import cl.perfumes.rincon_perfumes_back.model.entidades.PedidoEntidad;
import cl.perfumes.rincon_perfumes_back.service.EmailServicio;
import cl.perfumes.rincon_perfumes_back.service.PedidoServicio;
import cl.perfumes.rincon_perfumes_back.service.QrServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Map;

/**
 * Controlador para gestión de códigos QR de retiro
 */
@RestController
@RequestMapping("/api/qr")
@CrossOrigin(origins = "*")
public class QrControlador {

    @Autowired
    private QrServicio qrServicio;

    @Autowired
    private PedidoServicio pedidoServicio;

    @Autowired
    private EmailServicio emailServicio;

    /**
     * Genera QR para un pedido existente
     * POST /api/qr/generar/{pedidoId}
     */
    @PostMapping("/generar/{pedidoId}")
    public ResponseEntity<QrResponse> generarQr(@PathVariable Long pedidoId) {
        PedidoEntidad pedido = pedidoServicio.obtenerPorId(pedidoId)
                .orElse(null);
        
        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }
        
        // Generar QR
        PedidoEntidad pedidoConQr = qrServicio.generarQrParaPedido(pedido);
        
        // Enviar por email automáticamente
        emailServicio.enviarCorreoQrRetiro(pedidoConQr);
        pedidoConQr.setQrEnviado(true);
        pedidoServicio.guardar(pedidoConQr);
        
        QrResponse response = qrServicio.obtenerQrPorPedido(pedidoId);
        return ResponseEntity.ok(response);
    }

    /**
     * Obtiene el QR de un pedido
     * GET /api/qr/pedido/{pedidoId}
     */
    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<QrResponse> obtenerQrPedido(@PathVariable Long pedidoId) {
        try {
            QrResponse response = qrServicio.obtenerQrPorPedido(pedidoId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Descarga el QR como imagen PNG
     * GET /api/qr/descargar/{pedidoId}
     */
    @GetMapping("/descargar/{pedidoId}")
    public ResponseEntity<byte[]> descargarQr(@PathVariable Long pedidoId) {
        try {
            QrResponse qrResponse = qrServicio.obtenerQrPorPedido(pedidoId);
            
            if (qrResponse.getQrCodeBase64() == null) {
                return ResponseEntity.notFound().build();
            }
            
            byte[] imagenBytes = Base64.getDecoder().decode(qrResponse.getQrCodeBase64());
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            headers.setContentDispositionFormData("attachment", 
                    "qr_pedido_" + pedidoId + ".png");
            
            return new ResponseEntity<>(imagenBytes, headers, HttpStatus.OK);
            
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Valida un código de retiro (para empleados/escáner)
     * POST /api/qr/validar
     * Body: { "codigoRetiro": "RP-123-ABCD1234-5678" }
     */
    @PostMapping("/validar")
    public ResponseEntity<QrResponse> validarCodigo(@RequestBody Map<String, String> body) {
        String codigoRetiro = body.get("codigoRetiro");
        
        if (codigoRetiro == null || codigoRetiro.isEmpty()) {
            return ResponseEntity.badRequest().body(
                    QrResponse.builder()
                            .mensaje("Código de retiro requerido")
                            .build()
            );
        }
        
        try {
            QrResponse response = qrServicio.validarCodigoRetiro(codigoRetiro);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    QrResponse.builder()
                            .codigoRetiro(codigoRetiro)
                            .mensaje("❌ Código de retiro inválido o no encontrado")
                            .build()
            );
        }
    }

    /**
     * Valida desde datos del QR escaneado (JSON)
     * POST /api/qr/validar-scan
     * Body: {"tipo":"RETIRO_RINCON_PERFUMES","pedidoId":1,"codigo":"RP-1-ABC-1234",...}
     */
    @PostMapping("/validar-scan")
    public ResponseEntity<QrResponse> validarQrEscaneado(@RequestBody Map<String, Object> qrData) {
        try {
            String codigo = (String) qrData.get("codigo");
            String tipo = (String) qrData.get("tipo");
            
            // Verificar que es un QR válido de Rincón Perfumes
            if (!"RETIRO_RINCON_PERFUMES".equals(tipo)) {
                return ResponseEntity.badRequest().body(
                        QrResponse.builder()
                                .mensaje("❌ Este QR no es de Rincón Perfumes")
                                .build()
                );
            }
            
            QrResponse response = qrServicio.validarCodigoRetiro(codigo);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    QrResponse.builder()
                            .mensaje("❌ Error al validar QR: " + e.getMessage())
                            .build()
            );
        }
    }

    /**
     * Reenvía el QR por email
     * POST /api/qr/reenviar/{pedidoId}
     */
    @PostMapping("/reenviar/{pedidoId}")
    public ResponseEntity<QrResponse> reenviarQr(@PathVariable Long pedidoId) {
        try {
            QrResponse response = qrServicio.reenviarQr(pedidoId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    QrResponse.builder()
                            .pedidoId(pedidoId)
                            .mensaje("Error al reenviar QR: " + e.getMessage())
                            .build()
            );
        }
    }

    /**
     * Obtiene URL de WhatsApp para compartir
     * GET /api/qr/whatsapp/{pedidoId}
     */
    @GetMapping("/whatsapp/{pedidoId}")
    public ResponseEntity<Map<String, String>> obtenerUrlWhatsApp(@PathVariable Long pedidoId) {
        try {
            QrResponse qrResponse = qrServicio.obtenerQrPorPedido(pedidoId);
            
            return ResponseEntity.ok(Map.of(
                    "whatsappUrl", qrResponse.getWhatsappUrl() != null ? 
                            qrResponse.getWhatsappUrl() : "",
                    "mensaje", "URL generada correctamente"
            ));
            
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Estado del QR de un pedido
     * GET /api/qr/estado/{pedidoId}
     */
    @GetMapping("/estado/{pedidoId}")
    public ResponseEntity<Map<String, Object>> estadoQr(@PathVariable Long pedidoId) {
        try {
            QrResponse qrResponse = qrServicio.obtenerQrPorPedido(pedidoId);
            
            return ResponseEntity.ok(Map.of(
                    "pedidoId", pedidoId,
                    "tieneQr", qrResponse.getQrCodeBase64() != null,
                    "enviado", Boolean.TRUE.equals(qrResponse.getEnviado()),
                    "retirado", Boolean.TRUE.equals(qrResponse.getRetirado()),
                    "codigoRetiro", qrResponse.getCodigoRetiro() != null ? 
                            qrResponse.getCodigoRetiro() : ""
            ));
            
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
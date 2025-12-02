package cl.perfumes.rincon_perfumes_back.service.impl;

import cl.perfumes.rincon_perfumes_back.model.entidades.ClienteEntidad;
import cl.perfumes.rincon_perfumes_back.model.entidades.PedidoEntidad;
import cl.perfumes.rincon_perfumes_back.service.EmailServicio;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Locale;

@Service
public class EmailServicioImplementacion implements EmailServicio {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.from.name:Rincon Perfumes}")
    private String fromName;

    @Value("${mail.from.address:noreply@rinconperfumes.cl}")
    private String fromAddress;

    @Override
    public boolean enviarCorreoQrRetiro(PedidoEntidad pedido) {
        try {
            ClienteEntidad cliente = pedido.getCliente();
            String destinatario = cliente.getUsuario().getCorreo();
            String nombreCliente = cliente.getPrimerNombre();
            
            String asunto = " Tu pedido #" + pedido.getId() + " está listo - Código de Retiro";
            
            String contenidoHtml = generarPlantillaQrRetiro(pedido, nombreCliente);
            
            return enviarCorreo(
                    destinatario, 
                    asunto, 
                    contenidoHtml, 
                    "qr_retiro_pedido_" + pedido.getId() + ".png",
                    pedido.getQrCodeBase64()
            );
            
        } catch (Exception e) {
            System.err.println("Error al enviar correo QR: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean enviarCorreoConfirmacion(PedidoEntidad pedido) {
        try {
            ClienteEntidad cliente = pedido.getCliente();
            String destinatario = cliente.getUsuario().getCorreo();
            
            String asunto = " Confirmación de Pedido #" + pedido.getId() + " - Rincón Perfumes";
            String contenidoHtml = generarPlantillaConfirmacion(pedido);
            
            return enviarCorreo(destinatario, asunto, contenidoHtml, null, null);
            
        } catch (Exception e) {
            System.err.println("Error al enviar correo confirmación: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean enviarCorreo(String destinatario, String asunto, String contenidoHtml,
                                String nombreAdjunto, String adjuntoBase64) {
        try {
            MimeMessage mensaje = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true, "UTF-8");
            
            helper.setFrom(fromAddress, fromName);
            helper.setTo(destinatario);
            helper.setSubject(asunto);
            helper.setText(contenidoHtml, true);
            
            // Agregar adjunto si existe
            if (nombreAdjunto != null && adjuntoBase64 != null) {
                byte[] adjuntoBytes = Base64.getDecoder().decode(adjuntoBase64);
                helper.addAttachment(nombreAdjunto, new ByteArrayResource(adjuntoBytes));
            }
            
            mailSender.send(mensaje);
            System.out.println(" Correo enviado a: " + destinatario);
            return true;
            
        } catch (MessagingException | UnsupportedEncodingException e) {
            System.err.println(" Error al enviar correo: " + e.getMessage());
            return false;
        }
    }

    @Override
    public String generarUrlWhatsApp(String telefono, String mensaje) {
        // Limpiar número de teléfono (solo dígitos)
        String telefonoLimpio = telefono.replaceAll("[^0-9]", "");
        
        // Si no tiene código de país, agregar +56 (Chile)
        if (!telefonoLimpio.startsWith("56") && telefonoLimpio.length() == 9) {
            telefonoLimpio = "56" + telefonoLimpio;
        }
        
        try {
            String mensajeCodificado = URLEncoder.encode(mensaje, StandardCharsets.UTF_8.toString());
            return "https://wa.me/" + telefonoLimpio + "?text=" + mensajeCodificado;
        } catch (UnsupportedEncodingException e) {
            return "https://wa.me/" + telefonoLimpio;
        }
    }

    /**
     * Genera plantilla HTML para correo de QR de retiro
     */
    private String generarPlantillaQrRetiro(PedidoEntidad pedido, String nombreCliente) {
        NumberFormat formatoPrecio = NumberFormat.getCurrencyInstance(new Locale("es", "CL"));
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <style>
                    body { font-family: 'Segoe UI', Arial, sans-serif; background-color: #f5f5dc; margin: 0; padding: 20px; }
                    .container { max-width: 600px; margin: 0 auto; background: white; border-radius: 16px; overflow: hidden; box-shadow: 0 4px 20px rgba(0,0,0,0.1); }
                    .header { background: linear-gradient(135deg, #1a1a2e 0%%, #16213e 100%%); padding: 30px; text-align: center; }
                    .header h1 { color: #d4af37; margin: 0; font-size: 28px; }
                    .header p { color: #ffffff; margin: 10px 0 0 0; opacity: 0.9; }
                    .content { padding: 30px; }
                    .qr-section { text-align: center; padding: 20px; background: #f9f9f9; border-radius: 12px; margin: 20px 0; }
                    .qr-section img { max-width: 200px; border: 4px solid #d4af37; border-radius: 8px; }
                    .code-box { background: #1a1a2e; color: #d4af37; padding: 15px 25px; border-radius: 8px; font-size: 20px; font-weight: bold; letter-spacing: 2px; display: inline-block; margin: 15px 0; }
                    .info-table { width: 100%%; border-collapse: collapse; margin: 20px 0; }
                    .info-table td { padding: 12px; border-bottom: 1px solid #eee; }
                    .info-table td:first-child { color: #666; width: 40%%; }
                    .info-table td:last-child { font-weight: 600; color: #333; }
                    .total-row td { border-bottom: none; font-size: 18px; }
                    .total-row td:last-child { color: #d4af37; }
                    .footer { background: #f5f5f5; padding: 20px; text-align: center; color: #666; font-size: 13px; }
                    .btn { display: inline-block; background: #d4af37; color: #1a1a2e; padding: 12px 30px; border-radius: 25px; text-decoration: none; font-weight: bold; margin: 10px 5px; }
                    .warning { background: #fff3cd; border-left: 4px solid #ffc107; padding: 15px; margin: 20px 0; border-radius: 0 8px 8px 0; }
                </style>
            </head>
            <body>
                <div class="container">
                    <div class="header">
                        <h1> ¡Tu pedido está listo!</h1>
                        <p>Rincón Perfumes</p>
                    </div>
                    <div class="content">
                        <p>Hola <strong>%s</strong>,</p>
                        <p>Tu pedido <strong>#%d</strong> está listo para ser retirado. Presenta este código QR o el código de retiro en nuestra tienda.</p>
                        
                        <div class="qr-section">
                            <p style="margin: 0 0 15px 0; color: #666;">Escanea este código QR:</p>
                            <img src="cid:qrImage" alt="Código QR de Retiro"/>
                            <p style="margin: 15px 0 5px 0; color: #666;">O presenta este código:</p>
                            <div class="code-box">%s</div>
                        </div>
                        
                        <table class="info-table">
                            <tr>
                                <td> Fecha del pedido:</td>
                                <td>%s</td>
                            </tr>
                            <tr>
                                <td> Dirección de retiro:</td>
                                <td>Nuestra tienda principal</td>
                            </tr>
                            <tr class="total-row">
                                <td> Total a verificar:</td>
                                <td>%s</td>
                            </tr>
                        </table>
                        
                        <div class="warning">
                            <strong> Importante:</strong> El código QR es personal e intransferible. Solo puede ser utilizado una vez.
                        </div>
                        
                        <p style="text-align: center;">
                            <a href="#" class="btn"> Ver mi pedido</a>
                        </p>
                    </div>
                    <div class="footer">
                        <p>Gracias por tu compra en <strong>Rincón Perfumes</strong> 💐</p>
                        <p>Si tienes dudas, contáctanos por WhatsApp o en tienda.</p>
                    </div>
                </div>
            </body>
            </html>
            """.formatted(
                nombreCliente,
                pedido.getId(),
                pedido.getCodigoRetiro(),
                pedido.getFechaPedido().format(formatoFecha),
                formatoPrecio.format(pedido.getTotal())
        );
    }

    /**
     * Genera plantilla HTML para correo de confirmación
     */
    private String generarPlantillaConfirmacion(PedidoEntidad pedido) {
        NumberFormat formatoPrecio = NumberFormat.getCurrencyInstance(new Locale("es", "CL"));
        ClienteEntidad cliente = pedido.getCliente();
        
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <style>
                    body { font-family: Arial, sans-serif; background-color: #f5f5dc; padding: 20px; }
                    .container { max-width: 600px; margin: 0 auto; background: white; border-radius: 12px; padding: 30px; }
                    .header { text-align: center; color: #d4af37; }
                    .footer { text-align: center; color: #666; font-size: 12px; margin-top: 30px; }
                </style>
            </head>
            <body>
                <div class="container">
                    <div class="header">
                        <h1> Pedido Confirmado</h1>
                    </div>
                    <p>Hola <strong>%s</strong>,</p>
                    <p>Tu pedido <strong>#%d</strong> ha sido confirmado.</p>
                    <p><strong>Total:</strong> %s</p>
                    <p>Recibirás otro correo cuando esté listo para retiro.</p>
                    <div class="footer">
                        <p>Gracias por comprar en Rincón Perfumes 💐</p>
                    </div>
                </div>
            </body>
            </html>
            """.formatted(
                cliente.getPrimerNombre(),
                pedido.getId(),
                formatoPrecio.format(pedido.getTotal())
        );
    }
}
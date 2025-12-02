package cl.perfumes.rincon_perfumes_back.dto.response;

import lombok.*;
import java.time.LocalDateTime;

/**
 * DTO de respuesta para información del QR de retiro
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QrResponse {
    
    private Long pedidoId;
    private String codigoRetiro;
    private String qrCodeBase64;
    private String estado;
    private LocalDateTime fechaPedido;
    private String nombreCliente;
    private String correoCliente;
    private String telefonoCliente;
    private Boolean enviado;
    private Boolean retirado;
    private String mensaje;
    
    // URLs para compartir
    private String whatsappUrl;
    private String detalleUrl;
}
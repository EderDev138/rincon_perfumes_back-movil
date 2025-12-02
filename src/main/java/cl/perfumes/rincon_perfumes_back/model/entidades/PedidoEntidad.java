package cl.perfumes.rincon_perfumes_back.model.entidades;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntidad cliente;

    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime fechaPedido = LocalDateTime.now();

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;

    @Column(precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal descuento = BigDecimal.ZERO;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal iva;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @Column(nullable = false, length = 50)
    private String estado; // PENDIENTE, PAGADO, ENVIADO, etc.

    @Column(length = 255)
    private String direccionEnvio;

    @Column(length = 100)
    private String comunaEnvio;

    @Column(length = 100)
    private String regionEnvio;

    @Column(length = 100)
    private String numeroSeguimiento;

    @ManyToOne
    @JoinColumn(name = "promocion_id")
    private PromocionEntidad promocionAplicada;

    @Column
    @Builder.Default
    private LocalDateTime fechaActualizacion = LocalDateTime.now();

    // ============================================
    // CAMPOS PARA SISTEMA QR DE RETIRO
    // ============================================
    
    @Column(columnDefinition = "LONGTEXT")
    private String qrCodeBase64;

    @Column(length = 100, unique = true)
    private String codigoRetiro;

    @Column
    @Builder.Default
    private Boolean qrEnviado = false;
    
    @Column
    private LocalDateTime fechaRetiro;
    
    @Column
    @Builder.Default
    private Boolean retirado = false;
}
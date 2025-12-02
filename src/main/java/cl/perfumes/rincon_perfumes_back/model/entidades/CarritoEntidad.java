package cl.perfumes.rincon_perfumes_back.model.entidades;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "carrito",
       uniqueConstraints = @UniqueConstraint(columnNames = {"cliente_id", "producto_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarritoEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntidad cliente;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private ProductoEntidad producto;

    @Column(nullable = false)
    @Builder.Default
    private Integer cantidad = 1;

    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime fechaAgregado = LocalDateTime.now();

    @Column
    @Builder.Default
    private LocalDateTime fechaActualizacion = LocalDateTime.now();

    @Column(nullable = false)
    @Builder.Default
    private Boolean activo = true;
}

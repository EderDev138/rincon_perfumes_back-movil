package cl.perfumes.rincon_perfumes_back.model.entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @Column(nullable = false, length = 150)
    private String nombreProducto;

    @Column(length = 500)
    private String descripcion;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false, name = "volumen_ml")  // se cambio porque daba error en sql por el nombre con tilde
    private Integer volumenML;

    @ManyToOne
    @JoinColumn(name = "id_marca", nullable = false)
    private MarcaEntidad marca;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private CategoriaEntidad categoria;

    @ManyToOne
    @JoinColumn(name = "id_tipo_producto", nullable = false)
    private TipoProductoEntidad tipoProducto;

    @ManyToOne
    @JoinColumn(name = "id_genero", nullable = false)
    private GeneroEntidad genero;

    @Column(length = 100)
    private String aroma;

    @Column(length = 100)
    private String familiaOlfativa;

    @Column(length = 500)
    private String imagenUrl;

    @Column(nullable = false)
    @Builder.Default
    private Integer stock = 0;

    @Column(nullable = false)
    @Builder.Default
    private Boolean activo = true;

    @Column
    private java.time.LocalDateTime fechaCreacion;

    @Column
    private java.time.LocalDateTime fechaActualizacion;

    @PrePersist
    protected void onCreate() {
        fechaCreacion = java.time.LocalDateTime.now();
        fechaActualizacion = java.time.LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        fechaActualizacion = java.time.LocalDateTime.now();
    }

}
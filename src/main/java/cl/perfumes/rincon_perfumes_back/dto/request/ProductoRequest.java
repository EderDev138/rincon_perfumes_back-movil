package cl.perfumes.rincon_perfumes_back.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoRequest {
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private Long idMarca;
    private Long idCategoria;
    private Long idTipoProducto;
    private Long idGenero;
}

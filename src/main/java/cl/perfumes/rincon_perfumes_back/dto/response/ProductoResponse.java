package cl.perfumes.rincon_perfumes_back.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoResponse {
    private Long idProducto;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private String marca;
    private String categoria;
    private String tipoProducto;
    private String genero;
}

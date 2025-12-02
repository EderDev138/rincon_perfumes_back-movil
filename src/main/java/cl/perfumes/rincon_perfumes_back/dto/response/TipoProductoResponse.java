package cl.perfumes.rincon_perfumes_back.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoProductoResponse {
    private Long idTipoProducto;
    private String nombre;
    private String descripcion;
}


package cl.perfumes.rincon_perfumes_back.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoProductoRequest {
    private String nombre;
    private String descripcion;
}

package cl.perfumes.rincon_perfumes_back.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaResponse {
    private Long idCategoria;
    private String nombre;
    private String descripcion;
}


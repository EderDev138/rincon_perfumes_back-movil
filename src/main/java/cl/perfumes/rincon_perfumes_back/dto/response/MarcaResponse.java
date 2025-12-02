package cl.perfumes.rincon_perfumes_back.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarcaResponse {
    private Long idMarca;
    private String nombre;
    private String descripcion;
}


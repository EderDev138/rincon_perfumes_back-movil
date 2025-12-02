package cl.perfumes.rincon_perfumes_back.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponse {
    private Long idUsuario;
    private String nombre;
    private String apellido;
    private String correo;
    private Boolean activo;
}


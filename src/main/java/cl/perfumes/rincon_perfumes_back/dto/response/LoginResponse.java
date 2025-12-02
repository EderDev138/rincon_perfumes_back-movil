package cl.perfumes.rincon_perfumes_back.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
    private String mensaje;
    private String nombreUsuario;
    private boolean autenticado;
    // JWT token (null cuando la autenticación falla)
    private String token;
}

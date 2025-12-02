package cl.perfumes.rincon_perfumes_back.controller;

import cl.perfumes.rincon_perfumes_back.dto.request.LoginRequest;
import cl.perfumes.rincon_perfumes_back.dto.response.LoginResponse;
import cl.perfumes.rincon_perfumes_back.service.AuthServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthControlador {

    @Autowired
    private AuthServicio authServicio;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse respuesta = authServicio.login(request);
        return ResponseEntity.ok(respuesta);
    }
}


package cl.perfumes.rincon_perfumes_back.controller;

import cl.perfumes.rincon_perfumes_back.model.entidades.UsuarioEntidad;
import cl.perfumes.rincon_perfumes_back.service.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    // Listar todos los usuarios
    @GetMapping
    public ResponseEntity<List<UsuarioEntidad>> listarUsuarios() {
        return ResponseEntity.ok(usuarioServicio.listarTodos());
    }

    // Buscar usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntidad> buscarPorId(@PathVariable Long id) {
        Optional<UsuarioEntidad> usuario = usuarioServicio.buscarPorId(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear nuevo usuario
    @PostMapping
    public ResponseEntity<UsuarioEntidad> crearUsuario(@RequestBody UsuarioEntidad usuario) {
        if (usuarioServicio.existePorCorreo(usuario.getCorreo())) {
            return ResponseEntity.badRequest().build(); // correo ya existe
        }
        UsuarioEntidad nuevoUsuario = usuarioServicio.guardar(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    // Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioEntidad> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioEntidad usuarioActualizado) {
        return usuarioServicio.buscarPorId(id).map(usuario -> {
            usuario.setNombre(usuarioActualizado.getNombre());
            usuario.setApellido(usuarioActualizado.getApellido());
            usuario.setCorreo(usuarioActualizado.getCorreo());
            usuario.setContrasena(usuarioActualizado.getContrasena());
            usuario.setActivo(usuarioActualizado.getActivo());
            return ResponseEntity.ok(usuarioServicio.guardar(usuario));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        if (!usuarioServicio.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        usuarioServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}


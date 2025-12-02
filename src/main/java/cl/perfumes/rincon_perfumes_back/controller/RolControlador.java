package cl.perfumes.rincon_perfumes_back.controller;

import cl.perfumes.rincon_perfumes_back.model.entidades.RolEntidad;
import cl.perfumes.rincon_perfumes_back.service.RolServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*")
public class RolControlador {

    @Autowired
    private RolServicio rolServicio;

    // Listar todos los roles
    @GetMapping
    public ResponseEntity<List<RolEntidad>> listarRoles() {
        return ResponseEntity.ok(rolServicio.listarTodos());
    }

    // Buscar rol por ID
    @GetMapping("/{id}")
    public ResponseEntity<RolEntidad> buscarPorId(@PathVariable Long id) {
        Optional<RolEntidad> rol = rolServicio.buscarPorId(id);
        return rol.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear rol
    @PostMapping
    public ResponseEntity<RolEntidad> crearRol(@RequestBody RolEntidad rol) {
        if (rolServicio.existePorNombre(rol.getNombreRol())) {
            return ResponseEntity.badRequest().build(); // nombre duplicado
        }
        return ResponseEntity.ok(rolServicio.guardar(rol));
    }

    // Actualizar rol
    @PutMapping("/{id}")
    public ResponseEntity<RolEntidad> actualizarRol(@PathVariable Long id, @RequestBody RolEntidad rolActualizado) {
        return rolServicio.buscarPorId(id).map(rol -> {
            rol.setNombreRol(rolActualizado.getNombreRol());
            rol.setDescripcionRol(rolActualizado.getDescripcionRol());
            return ResponseEntity.ok(rolServicio.guardar(rol));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar rol
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRol(@PathVariable Long id) {
        if (!rolServicio.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        rolServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}


package cl.perfumes.rincon_perfumes_back.controller;

import cl.perfumes.rincon_perfumes_back.model.entidades.GeneroEntidad;
import cl.perfumes.rincon_perfumes_back.service.GeneroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/generos")
@CrossOrigin(origins = "*")
public class GeneroControlador {

    @Autowired
    private GeneroServicio generoServicio;

    @GetMapping
    public ResponseEntity<List<GeneroEntidad>> listarGeneros() {
        return ResponseEntity.ok(generoServicio.listarGeneros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneroEntidad> obtenerPorId(@PathVariable Long id) {
        return generoServicio.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GeneroEntidad> crear(@RequestBody GeneroEntidad genero) {
        return ResponseEntity.ok(generoServicio.guardar(genero));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneroEntidad> actualizar(@PathVariable Long id, @RequestBody GeneroEntidad genero) {
        return generoServicio.obtenerPorId(id)
                .map(existente -> {
                    genero.setIdGenero(id);
                    return ResponseEntity.ok(generoServicio.guardar(genero));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        generoServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}


package cl.perfumes.rincon_perfumes_back.controller;

import cl.perfumes.rincon_perfumes_back.model.entidades.CategoriaEntidad;
import cl.perfumes.rincon_perfumes_back.service.CategoriaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*")
public class CategoriaControlador {

    @Autowired
    private CategoriaServicio categoriaServicio;

    @GetMapping
    public ResponseEntity<List<CategoriaEntidad>> listarCategorias() {
        return ResponseEntity.ok(categoriaServicio.listarCategorias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaEntidad> obtenerPorId(@PathVariable Long id) {
        return categoriaServicio.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoriaEntidad> crear(@RequestBody CategoriaEntidad categoria) {
        return ResponseEntity.ok(categoriaServicio.guardar(categoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaEntidad> actualizar(@PathVariable Long id, @RequestBody CategoriaEntidad categoria) {
        return categoriaServicio.obtenerPorId(id)
                .map(existente -> {
                    categoria.setIdCategoria(id);
                    return ResponseEntity.ok(categoriaServicio.guardar(categoria));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        categoriaServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

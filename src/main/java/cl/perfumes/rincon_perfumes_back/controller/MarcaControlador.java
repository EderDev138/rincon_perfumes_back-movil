package cl.perfumes.rincon_perfumes_back.controller;

import cl.perfumes.rincon_perfumes_back.model.entidades.MarcaEntidad;
import cl.perfumes.rincon_perfumes_back.service.MarcaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marcas")
@CrossOrigin(origins = "*") // Permitir CORS (para pruebas front)
public class MarcaControlador {

    @Autowired
    private MarcaServicio marcaServicio;

    @GetMapping
    public ResponseEntity<List<MarcaEntidad>> listarMarcas() {
        return ResponseEntity.ok(marcaServicio.listarMarcas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaEntidad> obtenerPorId(@PathVariable Long id) {
        return marcaServicio.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MarcaEntidad> crear(@RequestBody MarcaEntidad marca) {
        return ResponseEntity.ok(marcaServicio.guardar(marca));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarcaEntidad> actualizar(@PathVariable Long id, @RequestBody MarcaEntidad marca) {
        return marcaServicio.obtenerPorId(id)
                .map(existente -> {
                    marca.setIdMarca(id);
                    return ResponseEntity.ok(marcaServicio.guardar(marca));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        marcaServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}


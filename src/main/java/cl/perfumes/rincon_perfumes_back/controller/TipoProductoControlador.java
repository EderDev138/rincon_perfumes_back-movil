package cl.perfumes.rincon_perfumes_back.controller;

import cl.perfumes.rincon_perfumes_back.model.entidades.TipoProductoEntidad;
import cl.perfumes.rincon_perfumes_back.service.TipoProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-producto")
@CrossOrigin(origins = "*")
public class TipoProductoControlador {

    @Autowired
    private TipoProductoServicio tipoProductoServicio;

    @GetMapping
    public ResponseEntity<List<TipoProductoEntidad>> listarTiposProducto() {
        return ResponseEntity.ok(tipoProductoServicio.listarTiposProducto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoProductoEntidad> obtenerPorId(@PathVariable Long id) {
        return tipoProductoServicio.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TipoProductoEntidad> crear(@RequestBody TipoProductoEntidad tipoProducto) {
        return ResponseEntity.ok(tipoProductoServicio.guardar(tipoProducto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoProductoEntidad> actualizar(@PathVariable Long id, @RequestBody TipoProductoEntidad tipoProducto) {
        return tipoProductoServicio.obtenerPorId(id)
                .map(existente -> {
                    tipoProducto.setIdTipoProducto(id);
                    return ResponseEntity.ok(tipoProductoServicio.guardar(tipoProducto));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        tipoProductoServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}


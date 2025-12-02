package cl.perfumes.rincon_perfumes_back.controller;

import cl.perfumes.rincon_perfumes_back.model.entidades.ProductoEntidad;
import cl.perfumes.rincon_perfumes_back.service.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoControlador {

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping
    public ResponseEntity<List<ProductoEntidad>> listarProductos() {
        return ResponseEntity.ok(productoServicio.listarProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoEntidad> obtenerPorId(@PathVariable Long id) {
        return productoServicio.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductoEntidad> crear(@RequestBody ProductoEntidad producto) {
        return ResponseEntity.ok(productoServicio.guardar(producto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoEntidad> actualizar(@PathVariable Long id, @RequestBody ProductoEntidad producto) {
        return productoServicio.obtenerPorId(id)
                .map(existente -> {
                    producto.setIdProducto(id);
                    return ResponseEntity.ok(productoServicio.guardar(producto));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

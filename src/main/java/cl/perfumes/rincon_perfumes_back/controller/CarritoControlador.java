package cl.perfumes.rincon_perfumes_back.controller;

import cl.perfumes.rincon_perfumes_back.model.entidades.CarritoEntidad;
import cl.perfumes.rincon_perfumes_back.service.CarritoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/carrito")
@CrossOrigin(origins = "*")
public class CarritoControlador {

    @Autowired
    private CarritoServicio carritoServicio;

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<CarritoEntidad>> listarPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(carritoServicio.listarPorCliente(clienteId));
    }

    @PostMapping
    public ResponseEntity<CarritoEntidad> agregarProducto(@RequestBody CarritoEntidad carrito) {
        return ResponseEntity.ok(carritoServicio.guardar(carrito));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        carritoServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/vaciar/{clienteId}")
    public ResponseEntity<Void> vaciarCarrito(@PathVariable Long clienteId) {
        carritoServicio.vaciarCarrito(clienteId);
        return ResponseEntity.noContent().build();
    }
}

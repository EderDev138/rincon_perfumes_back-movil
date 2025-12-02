package cl.perfumes.rincon_perfumes_back.controller;

import cl.perfumes.rincon_perfumes_back.model.entidades.DetallePedidoEntidad;
import cl.perfumes.rincon_perfumes_back.service.DetallePedidoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/detalles-pedido")
@CrossOrigin(origins = "*")
public class DetallePedidoControlador {

    @Autowired
    private DetallePedidoServicio detallePedidoServicio;

    @GetMapping
    public ResponseEntity<List<DetallePedidoEntidad>> listarDetalles() {
        return ResponseEntity.ok(detallePedidoServicio.listarDetalles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallePedidoEntidad> obtenerPorId(@PathVariable Long id) {
        return detallePedidoServicio.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<List<DetallePedidoEntidad>> listarPorPedido(@PathVariable Long pedidoId) {
        return ResponseEntity.ok(detallePedidoServicio.listarPorPedido(pedidoId));
    }

    @PostMapping
    public ResponseEntity<DetallePedidoEntidad> crearDetalle(@RequestBody DetallePedidoEntidad detalle) {
        return ResponseEntity.ok(detallePedidoServicio.guardar(detalle));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetallePedidoEntidad> actualizarDetalle(@PathVariable Long id, @RequestBody DetallePedidoEntidad detalleActualizado) {
        return detallePedidoServicio.obtenerPorId(id)
                .map(detalle -> {
                    detalleActualizado.setId(id);
                    return ResponseEntity.ok(detallePedidoServicio.guardar(detalleActualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDetalle(@PathVariable Long id) {
        detallePedidoServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}


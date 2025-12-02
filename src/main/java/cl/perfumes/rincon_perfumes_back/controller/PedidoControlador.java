package cl.perfumes.rincon_perfumes_back.controller;

import cl.perfumes.rincon_perfumes_back.model.entidades.PedidoEntidad;
import cl.perfumes.rincon_perfumes_back.service.PedidoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoControlador {

    @Autowired
    private PedidoServicio pedidoServicio;

    @GetMapping
    public ResponseEntity<List<PedidoEntidad>> listarPedidos() {
        return ResponseEntity.ok(pedidoServicio.listarPedidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoEntidad> obtenerPorId(@PathVariable Long id) {
        return pedidoServicio.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<PedidoEntidad>> listarPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(pedidoServicio.listarPorCliente(clienteId));
    }

    @PostMapping
    public ResponseEntity<PedidoEntidad> crearPedido(@RequestBody PedidoEntidad pedido) {
        return ResponseEntity.ok(pedidoServicio.guardar(pedido));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoEntidad> actualizarPedido(@PathVariable Long id, @RequestBody PedidoEntidad pedidoActualizado) {
        return pedidoServicio.obtenerPorId(id)
                .map(pedido -> {
                    pedidoActualizado.setId(id);
                    return ResponseEntity.ok(pedidoServicio.guardar(pedidoActualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id) {
        pedidoServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

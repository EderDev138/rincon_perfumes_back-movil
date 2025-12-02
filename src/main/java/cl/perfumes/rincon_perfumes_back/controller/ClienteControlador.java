package cl.perfumes.rincon_perfumes_back.controller;

import cl.perfumes.rincon_perfumes_back.model.entidades.ClienteEntidad;
import cl.perfumes.rincon_perfumes_back.service.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteControlador {

    @Autowired
    private ClienteServicio clienteServicio;

    @GetMapping
    public ResponseEntity<List<ClienteEntidad>> listarClientes() {
        return ResponseEntity.ok(clienteServicio.listarClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteEntidad> obtenerPorId(@PathVariable Long id) {
        return clienteServicio.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClienteEntidad> crearCliente(@RequestBody ClienteEntidad cliente) {
        return ResponseEntity.ok(clienteServicio.guardar(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteEntidad> actualizarCliente(@PathVariable Long id, @RequestBody ClienteEntidad clienteActualizado) {
        return clienteServicio.obtenerPorId(id)
                .map(cliente -> {
                    clienteActualizado.setId(id);
                    return ResponseEntity.ok(clienteServicio.guardar(clienteActualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}


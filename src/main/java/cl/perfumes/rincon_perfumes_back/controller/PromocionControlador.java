package cl.perfumes.rincon_perfumes_back.controller;

import cl.perfumes.rincon_perfumes_back.model.entidades.PromocionEntidad;
import cl.perfumes.rincon_perfumes_back.service.PromocionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/promociones")
@CrossOrigin(origins = "*")
public class PromocionControlador {

    @Autowired
    private PromocionServicio promocionServicio;

    @GetMapping
    public ResponseEntity<List<PromocionEntidad>> listarPromociones() {
        return ResponseEntity.ok(promocionServicio.listarPromociones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PromocionEntidad> obtenerPorId(@PathVariable Long id) {
        return promocionServicio.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<PromocionEntidad> buscarPorCodigo(@PathVariable String codigo) {
        return promocionServicio.buscarPorCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PromocionEntidad> crearPromocion(@RequestBody PromocionEntidad promocion) {
        return ResponseEntity.ok(promocionServicio.guardar(promocion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PromocionEntidad> actualizarPromocion(@PathVariable Long id, @RequestBody PromocionEntidad promoActualizada) {
        return promocionServicio.obtenerPorId(id)
                .map(promo -> {
                    promoActualizada.setId(id);
                    return ResponseEntity.ok(promocionServicio.guardar(promoActualizada));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPromocion(@PathVariable Long id) {
        promocionServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

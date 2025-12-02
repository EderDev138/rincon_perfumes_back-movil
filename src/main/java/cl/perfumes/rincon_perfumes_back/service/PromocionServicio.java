package cl.perfumes.rincon_perfumes_back.service;

import cl.perfumes.rincon_perfumes_back.model.entidades.PromocionEntidad;
import java.util.List;
import java.util.Optional;

public interface PromocionServicio {
    List<PromocionEntidad> listarPromociones();
    Optional<PromocionEntidad> obtenerPorId(Long id);
    Optional<PromocionEntidad> buscarPorCodigo(String codigo);
    PromocionEntidad guardar(PromocionEntidad promocion);
    void eliminar(Long id);
}

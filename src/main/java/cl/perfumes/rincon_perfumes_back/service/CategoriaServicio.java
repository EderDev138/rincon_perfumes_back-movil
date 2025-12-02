package cl.perfumes.rincon_perfumes_back.service;

import cl.perfumes.rincon_perfumes_back.model.entidades.CategoriaEntidad;
import java.util.List;
import java.util.Optional;

public interface CategoriaServicio {
    List<CategoriaEntidad> listarCategorias();
    Optional<CategoriaEntidad> obtenerPorId(Long id);
    CategoriaEntidad guardar(CategoriaEntidad categoria);
    void eliminar(Long id);
}

package cl.perfumes.rincon_perfumes_back.service;

import cl.perfumes.rincon_perfumes_back.model.entidades.GeneroEntidad;
import java.util.List;
import java.util.Optional;

public interface GeneroServicio {
    List<GeneroEntidad> listarGeneros();
    Optional<GeneroEntidad> obtenerPorId(Long id);
    GeneroEntidad guardar(GeneroEntidad genero);
    void eliminar(Long id);
}


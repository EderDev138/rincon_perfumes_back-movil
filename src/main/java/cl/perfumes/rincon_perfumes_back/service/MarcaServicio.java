package cl.perfumes.rincon_perfumes_back.service;

import cl.perfumes.rincon_perfumes_back.model.entidades.MarcaEntidad;
import java.util.List;
import java.util.Optional;

public interface MarcaServicio {
    List<MarcaEntidad> listarMarcas();
    Optional<MarcaEntidad> obtenerPorId(Long id);
    MarcaEntidad guardar(MarcaEntidad marca);
    void eliminar(Long id);
}


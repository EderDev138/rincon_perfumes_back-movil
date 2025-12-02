package cl.perfumes.rincon_perfumes_back.service;

import cl.perfumes.rincon_perfumes_back.model.entidades.RolEntidad;

import java.util.List;
import java.util.Optional;

public interface RolServicio {

    List<RolEntidad> listarTodos();
    Optional<RolEntidad> buscarPorId(Long id);
    RolEntidad guardar(RolEntidad rol);
    void eliminar(Long id);
    boolean existePorNombre(String nombreRol);
}

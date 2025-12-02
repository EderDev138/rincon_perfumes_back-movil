package cl.perfumes.rincon_perfumes_back.service;

import cl.perfumes.rincon_perfumes_back.model.entidades.UsuarioEntidad;

import java.util.List;
import java.util.Optional;

public interface UsuarioServicio {

    List<UsuarioEntidad> listarTodos();
    Optional<UsuarioEntidad> buscarPorId(Long id);
    Optional<UsuarioEntidad> buscarPorCorreo(String correo);
    UsuarioEntidad guardar(UsuarioEntidad usuario);
    void eliminar(Long id);
    boolean existePorCorreo(String correo);
}

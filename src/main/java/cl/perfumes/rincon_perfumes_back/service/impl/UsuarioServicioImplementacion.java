package cl.perfumes.rincon_perfumes_back.service.impl;

import cl.perfumes.rincon_perfumes_back.model.entidades.UsuarioEntidad;
import cl.perfumes.rincon_perfumes_back.repository.UsuarioRepositorio;
import cl.perfumes.rincon_perfumes_back.service.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicioImplementacion implements UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public List<UsuarioEntidad> listarTodos() {
        return usuarioRepositorio.findAll();
    }

    @Override
    public Optional<UsuarioEntidad> buscarPorId(Long id) {
        return usuarioRepositorio.findById(id);
    }

    @Override
    public Optional<UsuarioEntidad> buscarPorCorreo(String correo) {
        return usuarioRepositorio.findByCorreo(correo);
    }

    @Override
    public UsuarioEntidad guardar(UsuarioEntidad usuario) {
        return usuarioRepositorio.save(usuario);
    }

    

    @Override
    public void eliminar(Long id) {
        usuarioRepositorio.deleteById(id);
    }

    @Override
    public boolean existePorCorreo(String correo) {
        return usuarioRepositorio.existsByCorreo(correo);
    }
}

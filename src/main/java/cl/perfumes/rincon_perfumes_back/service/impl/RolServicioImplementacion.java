package cl.perfumes.rincon_perfumes_back.service.impl;

import cl.perfumes.rincon_perfumes_back.model.entidades.RolEntidad;
import cl.perfumes.rincon_perfumes_back.repository.RolRepositorio;
import cl.perfumes.rincon_perfumes_back.service.RolServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolServicioImplementacion implements RolServicio {

    @Autowired
    private RolRepositorio rolRepositorio;

    @Override
    public List<RolEntidad> listarTodos() {
        return rolRepositorio.findAll();
    }

    @Override
    public Optional<RolEntidad> buscarPorId(Long id) {
        return rolRepositorio.findById(id);
    }

    @Override
    public RolEntidad guardar(RolEntidad rol) {
        return rolRepositorio.save(rol);
    }

    @Override
    public void eliminar(Long id) {
        rolRepositorio.deleteById(id);
    }

    @Override
    public boolean existePorNombre(String nombreRol) {
        return rolRepositorio.existsByNombreRol(nombreRol);
    }
}

package cl.perfumes.rincon_perfumes_back.service.impl;

import cl.perfumes.rincon_perfumes_back.model.entidades.GeneroEntidad;
import cl.perfumes.rincon_perfumes_back.repository.GeneroRepositorio;
import cl.perfumes.rincon_perfumes_back.service.GeneroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneroServicioImplementacion implements GeneroServicio {

    @Autowired
    private GeneroRepositorio generoRepositorio;

    @Override
    public List<GeneroEntidad> listarGeneros() {
        return generoRepositorio.findAll();
    }

    @Override
    public Optional<GeneroEntidad> obtenerPorId(Long id) {
        return generoRepositorio.findById(id);
    }

    @Override
    public GeneroEntidad guardar(GeneroEntidad genero) {
        return generoRepositorio.save(genero);
    }

    @Override
    public void eliminar(Long id) {
        generoRepositorio.deleteById(id);
    }
}


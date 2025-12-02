package cl.perfumes.rincon_perfumes_back.service.impl;

import cl.perfumes.rincon_perfumes_back.model.entidades.MarcaEntidad;
import cl.perfumes.rincon_perfumes_back.repository.MarcaRepositorio;
import cl.perfumes.rincon_perfumes_back.service.MarcaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaServicioImplementacion implements MarcaServicio {

    @Autowired
    private MarcaRepositorio marcaRepositorio;

    @Override
    public List<MarcaEntidad> listarMarcas() {
        return marcaRepositorio.findAll();
    }

    @Override
    public Optional<MarcaEntidad> obtenerPorId(Long id) {
        return marcaRepositorio.findById(id);
    }

    @Override
    public MarcaEntidad guardar(MarcaEntidad marca) {
        return marcaRepositorio.save(marca);
    }

    @Override
    public void eliminar(Long id) {
        marcaRepositorio.deleteById(id);
    }
}

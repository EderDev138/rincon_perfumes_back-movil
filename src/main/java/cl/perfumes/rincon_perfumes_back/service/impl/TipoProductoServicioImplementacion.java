package cl.perfumes.rincon_perfumes_back.service.impl;

import cl.perfumes.rincon_perfumes_back.model.entidades.TipoProductoEntidad;
import cl.perfumes.rincon_perfumes_back.repository.TipoProductoRepositorio;
import cl.perfumes.rincon_perfumes_back.service.TipoProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoProductoServicioImplementacion implements TipoProductoServicio {

    @Autowired
    private TipoProductoRepositorio tipoProductoRepositorio;

    @Override
    public List<TipoProductoEntidad> listarTiposProducto() {
        return tipoProductoRepositorio.findAll();
    }

    @Override
    public Optional<TipoProductoEntidad> obtenerPorId(Long id) {
        return tipoProductoRepositorio.findById(id);
    }

    @Override
    public TipoProductoEntidad guardar(TipoProductoEntidad tipoProducto) {
        return tipoProductoRepositorio.save(tipoProducto);
    }

    @Override
    public void eliminar(Long id) {
        tipoProductoRepositorio.deleteById(id);
    }
}


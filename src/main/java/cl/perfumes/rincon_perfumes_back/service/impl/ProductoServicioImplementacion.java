package cl.perfumes.rincon_perfumes_back.service.impl;

import cl.perfumes.rincon_perfumes_back.model.entidades.ProductoEntidad;
import cl.perfumes.rincon_perfumes_back.repository.ProductoRepositorio;
import cl.perfumes.rincon_perfumes_back.service.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImplementacion implements ProductoServicio {

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Override
    public List<ProductoEntidad> listarProductos() {
        return productoRepositorio.findAll();
    }

    @Override
    public Optional<ProductoEntidad> obtenerPorId(Long id) {
        return productoRepositorio.findById(id);
    }

    @Override
    public ProductoEntidad guardar(ProductoEntidad producto) {
        return productoRepositorio.save(producto);
    }

    @Override
    public void eliminar(Long id) {
        productoRepositorio.deleteById(id);
    }
}


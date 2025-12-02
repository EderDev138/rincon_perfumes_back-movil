package cl.perfumes.rincon_perfumes_back.service;

import cl.perfumes.rincon_perfumes_back.model.entidades.ProductoEntidad;
import java.util.List;
import java.util.Optional;

public interface ProductoServicio {
    List<ProductoEntidad> listarProductos();
    Optional<ProductoEntidad> obtenerPorId(Long id);
    ProductoEntidad guardar(ProductoEntidad producto);
    void eliminar(Long id);
}


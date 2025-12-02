package cl.perfumes.rincon_perfumes_back.service;

import cl.perfumes.rincon_perfumes_back.model.entidades.TipoProductoEntidad;
import java.util.List;
import java.util.Optional;

public interface TipoProductoServicio {
    List<TipoProductoEntidad> listarTiposProducto();
    Optional<TipoProductoEntidad> obtenerPorId(Long id);
    TipoProductoEntidad guardar(TipoProductoEntidad tipoProducto);
    void eliminar(Long id);
}


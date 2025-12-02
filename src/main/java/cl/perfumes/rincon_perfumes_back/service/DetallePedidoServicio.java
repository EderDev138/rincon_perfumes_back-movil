package cl.perfumes.rincon_perfumes_back.service;

import cl.perfumes.rincon_perfumes_back.model.entidades.DetallePedidoEntidad;
import java.util.List;
import java.util.Optional;

public interface DetallePedidoServicio {
    List<DetallePedidoEntidad> listarDetalles();
    Optional<DetallePedidoEntidad> obtenerPorId(Long id);
    List<DetallePedidoEntidad> listarPorPedido(Long pedidoId);
    DetallePedidoEntidad guardar(DetallePedidoEntidad detalle);
    void eliminar(Long id);
}


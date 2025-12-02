package cl.perfumes.rincon_perfumes_back.service;

import cl.perfumes.rincon_perfumes_back.model.entidades.PedidoEntidad;
import java.util.List;
import java.util.Optional;

public interface PedidoServicio {
    List<PedidoEntidad> listarPedidos();
    Optional<PedidoEntidad> obtenerPorId(Long id);
    List<PedidoEntidad> listarPorCliente(Long clienteId);
    PedidoEntidad guardar(PedidoEntidad pedido);
    void eliminar(Long id);
}


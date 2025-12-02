package cl.perfumes.rincon_perfumes_back.service.impl;

import cl.perfumes.rincon_perfumes_back.model.entidades.PedidoEntidad;
import cl.perfumes.rincon_perfumes_back.repository.PedidoRepositorio;
import cl.perfumes.rincon_perfumes_back.service.PedidoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServicioImplementacion implements PedidoServicio {

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Override
    public List<PedidoEntidad> listarPedidos() {
        return pedidoRepositorio.findAll();
    }

    @Override
    public Optional<PedidoEntidad> obtenerPorId(Long id) {
        return pedidoRepositorio.findById(id);
    }

    @Override
    public List<PedidoEntidad> listarPorCliente(Long clienteId) {
        return pedidoRepositorio.findByClienteId(clienteId);
    }

    @Override
    public PedidoEntidad guardar(PedidoEntidad pedido) {
        return pedidoRepositorio.save(pedido);
    }

    @Override
    public void eliminar(Long id) {
        pedidoRepositorio.deleteById(id);
    }
}


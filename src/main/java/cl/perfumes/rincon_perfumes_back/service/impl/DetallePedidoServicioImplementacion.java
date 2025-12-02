package cl.perfumes.rincon_perfumes_back.service.impl;

import cl.perfumes.rincon_perfumes_back.model.entidades.DetallePedidoEntidad;
import cl.perfumes.rincon_perfumes_back.repository.DetallePedidoRepositorio;
import cl.perfumes.rincon_perfumes_back.service.DetallePedidoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DetallePedidoServicioImplementacion implements DetallePedidoServicio {

    @Autowired
    private DetallePedidoRepositorio detallePedidoRepositorio;

    @Override
    public List<DetallePedidoEntidad> listarDetalles() {
        return detallePedidoRepositorio.findAll();
    }

    @Override
    public Optional<DetallePedidoEntidad> obtenerPorId(Long id) {
        return detallePedidoRepositorio.findById(id);
    }

    @Override
    public List<DetallePedidoEntidad> listarPorPedido(Long pedidoId) {
        return detallePedidoRepositorio.findByPedidoId(pedidoId);
    }

    @Override
    public DetallePedidoEntidad guardar(DetallePedidoEntidad detalle) {
        return detallePedidoRepositorio.save(detalle);
    }

    @Override
    public void eliminar(Long id) {
        detallePedidoRepositorio.deleteById(id);
    }
}

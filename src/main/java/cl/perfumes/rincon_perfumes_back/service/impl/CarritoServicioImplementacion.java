package cl.perfumes.rincon_perfumes_back.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.perfumes.rincon_perfumes_back.model.entidades.CarritoEntidad;
import cl.perfumes.rincon_perfumes_back.repository.CarritoRepositorio;
import cl.perfumes.rincon_perfumes_back.service.CarritoServicio;
import jakarta.transaction.Transactional;

@Service
public class CarritoServicioImplementacion implements CarritoServicio {

    @Autowired
    private CarritoRepositorio carritoRepositorio;

    @Override
    public List<CarritoEntidad> listarPorCliente(Long clienteId) {
        return carritoRepositorio.findByClienteId(clienteId);
    }

    @Override
    public Optional<CarritoEntidad> obtenerPorClienteYProducto(Long clienteId, Long productoId) {
        return carritoRepositorio.findByClienteIdAndProducto_IdProducto(clienteId, productoId);
    }

    @Override
    public CarritoEntidad guardar(CarritoEntidad carrito) {
        return carritoRepositorio.save(carrito);
    }

    @Override
    public void eliminar(Long id) {
        carritoRepositorio.deleteById(id);
    }

    @Override
    @Transactional
    public void vaciarCarrito(Long clienteId) {
        carritoRepositorio.deleteByClienteId(clienteId);
    }
}


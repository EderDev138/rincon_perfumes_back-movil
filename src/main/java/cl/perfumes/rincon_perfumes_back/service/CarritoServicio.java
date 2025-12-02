package cl.perfumes.rincon_perfumes_back.service;

import cl.perfumes.rincon_perfumes_back.model.entidades.CarritoEntidad;
import java.util.List;
import java.util.Optional;

public interface CarritoServicio {
    List<CarritoEntidad> listarPorCliente(Long clienteId);
    Optional<CarritoEntidad> obtenerPorClienteYProducto(Long clienteId, Long productoId);
    CarritoEntidad guardar(CarritoEntidad carrito);
    void eliminar(Long id);
    void vaciarCarrito(Long clienteId);
}


package cl.perfumes.rincon_perfumes_back.repository;

import cl.perfumes.rincon_perfumes_back.model.entidades.CarritoEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarritoRepositorio extends JpaRepository<CarritoEntidad, Long> {
    List<CarritoEntidad> findByClienteId(Long clienteId);
    Optional<CarritoEntidad> findByClienteIdAndProducto_IdProducto(Long clienteId, Long idProducto);
    void deleteByClienteId(Long clienteId);
}

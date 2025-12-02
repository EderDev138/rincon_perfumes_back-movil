package cl.perfumes.rincon_perfumes_back.repository;

import cl.perfumes.rincon_perfumes_back.model.entidades.TipoProductoEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoProductoRepositorio extends JpaRepository<TipoProductoEntidad, Long> {
    boolean existsByNombreTipo(String nombreTipo);
}


package cl.perfumes.rincon_perfumes_back.repository;

import cl.perfumes.rincon_perfumes_back.model.entidades.PromocionEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PromocionRepositorio extends JpaRepository<PromocionEntidad, Long> {
    Optional<PromocionEntidad> findByCodigoPromocional(String codigoPromocional);
    Optional<PromocionEntidad> findByActivoTrueAndFechaInicioBeforeAndFechaFinAfter(LocalDate inicio, LocalDate fin);
}

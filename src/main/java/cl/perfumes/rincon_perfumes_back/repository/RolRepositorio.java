package cl.perfumes.rincon_perfumes_back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.perfumes.rincon_perfumes_back.model.entidades.RolEntidad;

@Repository
public interface RolRepositorio extends JpaRepository<RolEntidad, Long> {
    boolean existsByNombreRol(String nombreRol);
    Optional<RolEntidad> findByNombreRol(String nombreRol);
    
}


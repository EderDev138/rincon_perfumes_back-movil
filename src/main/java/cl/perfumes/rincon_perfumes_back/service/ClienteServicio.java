package cl.perfumes.rincon_perfumes_back.service;

import cl.perfumes.rincon_perfumes_back.model.entidades.ClienteEntidad;
import java.util.List;
import java.util.Optional;

public interface ClienteServicio {
    List<ClienteEntidad> listarClientes();
    Optional<ClienteEntidad> obtenerPorId(Long id);
    Optional<ClienteEntidad> obtenerPorRut(String rut);
    ClienteEntidad guardar(ClienteEntidad cliente);
    void eliminar(Long id);
}

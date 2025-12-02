package cl.perfumes.rincon_perfumes_back.service.impl;

import cl.perfumes.rincon_perfumes_back.model.entidades.PromocionEntidad;
import cl.perfumes.rincon_perfumes_back.repository.PromocionRepositorio;
import cl.perfumes.rincon_perfumes_back.service.PromocionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PromocionServicioImplementacion implements PromocionServicio {

    @Autowired
    private PromocionRepositorio promocionRepositorio;

    @Override
    public List<PromocionEntidad> listarPromociones() {
        return promocionRepositorio.findAll();
    }

    @Override
    public Optional<PromocionEntidad> obtenerPorId(Long id) {
        return promocionRepositorio.findById(id);
    }

    @Override
    public Optional<PromocionEntidad> buscarPorCodigo(String codigo) {
        return promocionRepositorio.findByCodigoPromocional(codigo);
    }

    @Override
    public PromocionEntidad guardar(PromocionEntidad promocion) {
        return promocionRepositorio.save(promocion);
    }

    @Override
    public void eliminar(Long id) {
        promocionRepositorio.deleteById(id);
    }
}


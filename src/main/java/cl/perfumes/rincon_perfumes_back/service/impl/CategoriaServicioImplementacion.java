
package cl.perfumes.rincon_perfumes_back.service.impl;

import cl.perfumes.rincon_perfumes_back.model.entidades.CategoriaEntidad;
import cl.perfumes.rincon_perfumes_back.repository.CategoriaRepositorio;
import cl.perfumes.rincon_perfumes_back.service.CategoriaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServicioImplementacion implements CategoriaServicio {

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Override
    public List<CategoriaEntidad> listarCategorias() {
        return categoriaRepositorio.findAll();
    }

    @Override
    public Optional<CategoriaEntidad> obtenerPorId(Long id) {
        return categoriaRepositorio.findById(id);
    }

    @Override
    public CategoriaEntidad guardar(CategoriaEntidad categoria) {
        return categoriaRepositorio.save(categoria);
    }

    @Override
    public void eliminar(Long id) {
        categoriaRepositorio.deleteById(id);
    }
}

package cl.perfumes.rincon_perfumes_back.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional; // <--- 1. Importar Collections

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cl.perfumes.rincon_perfumes_back.model.entidades.ClienteEntidad;
import cl.perfumes.rincon_perfumes_back.model.entidades.RolEntidad;
import cl.perfumes.rincon_perfumes_back.model.entidades.UsuarioEntidad; // <--- 1. Importar Entidad Rol
import cl.perfumes.rincon_perfumes_back.repository.ClienteRepositorio;
import cl.perfumes.rincon_perfumes_back.repository.RolRepositorio; // <--- 1. Importar Repositorio Rol
import cl.perfumes.rincon_perfumes_back.service.ClienteServicio;
import cl.perfumes.rincon_perfumes_back.service.UsuarioServicio;
import cl.perfumes.rincon_perfumes_back.util.Constants; // <--- 1. Importar Constantes (ROL_CLIENTE)
import jakarta.transaction.Transactional;

@Service
public class ClienteServicioImplementacion implements ClienteServicio {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired 
    private UsuarioServicio usuarioServicio;

    @Autowired // <--- 2. Inyectar el repositorio
    private RolRepositorio rolRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public ClienteEntidad guardar(ClienteEntidad cliente) {
        
        UsuarioEntidad usuario = cliente.getUsuario();

        // Corrección de ID para nuevos registros
        if (usuario.getIdUsuario() != null && usuario.getIdUsuario() == 0L) {
            usuario.setIdUsuario(null);
        }


        // LÓGICA DE ASIGNACIÓN AUTOMÁTICA DE ROL

        String claveEncriptada = passwordEncoder.encode(usuario.getContrasena());
        usuario.setContrasena(claveEncriptada);
        
        if (usuario.getRoles() == null || usuario.getRoles().isEmpty()) {
        
            RolEntidad rolCliente = rolRepositorio.findByNombreRol(Constants.ROL_CLIENTE)
                    .orElseThrow(() -> new RuntimeException("Error: El Rol 'CLIENTE' no existe en la base de datos."));
            
       
            usuario.setRoles(Collections.singleton(rolCliente));
        }
   
        
        // Guardar Usuario con rol
        UsuarioEntidad usuarioPersistido = usuarioServicio.guardar(usuario); 
        
        // Asignar al Cliente y guardar
        cliente.setUsuario(usuarioPersistido); 
        return clienteRepositorio.save(cliente); 
    }

    @Override
    public List<ClienteEntidad> listarClientes() {
        return clienteRepositorio.findAll();
    }

    @Override
    public Optional<ClienteEntidad> obtenerPorId(Long id) {
        return clienteRepositorio.findById(id);
    }

    @Override
    public Optional<ClienteEntidad> obtenerPorRut(String rut) {
        return clienteRepositorio.findByRut(rut);
    }


    @Override
    public void eliminar(Long id) {
        clienteRepositorio.deleteById(id);
    }
}

package com.example.demo.servicio;

import com.example.demo.entidades.Administrador;
import com.example.demo.repositorio.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdministradorServiceImpl implements AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    // Implementación del método para autenticar administrador
    @Override
    public Optional<Administrador> authenticate(String correo, String contrasena) {
        return administradorRepository.findByCorreoAndContrasena(correo, contrasena);
    }

    // Implementación del método para obtener todos los administradores
    @Override
    public List<Administrador> getAllAdministradores() {
        return administradorRepository.findAll();
    }

    // Implementación del método para obtener un administrador por ID
    @Override
    public Optional<Administrador> getAdministradorById(Long id) {
        return administradorRepository.findById(id);
    }

    // Implementación del método para crear un nuevo administrador
    @Override
    public Administrador createAdministrador(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    // Implementación del método para actualizar un administrador
    @Override
    public Optional<Administrador> updateAdministrador(Long id, Administrador administradorDetails) {
        return administradorRepository.findById(id).map(administrador -> {
            administrador.setNombre(administradorDetails.getNombre());
            administrador.setCorreo(administradorDetails.getCorreo());
            administrador.setTelefono(administradorDetails.getTelefono());
            return administradorRepository.save(administrador);
        });
    }

    // Implementación del método para eliminar un administrador por ID
    @Override
    public boolean deleteAdministrador(Long id) {
        if (administradorRepository.existsById(id)) {
            administradorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

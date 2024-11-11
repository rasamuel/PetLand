package com.example.demo.servicio;

import com.example.demo.entidades.Administrador;
import java.util.List;
import java.util.Optional;

public interface AdministradorService {

    // Método para autenticar administrador
    Optional<Administrador> authenticate(String correo, String contrasena);

    // Métodos adicionales para CRUD
    List<Administrador> getAllAdministradores();
    Optional<Administrador> getAdministradorById(Long id);
    Administrador createAdministrador(Administrador administrador);
    Optional<Administrador> updateAdministrador(Long id, Administrador administradorDetails);
    boolean deleteAdministrador(Long id);
}

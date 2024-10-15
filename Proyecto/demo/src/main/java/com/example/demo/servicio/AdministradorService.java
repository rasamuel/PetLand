package com.example.demo.servicio;

import com.example.demo.entidades.Administrador;

import java.util.Optional;

public interface AdministradorService {

    // Método para autenticar administrador
    Optional<Administrador> authenticate(String correo, String contrasena);
}

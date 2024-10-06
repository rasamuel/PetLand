package com.example.demo.servicio;

import com.example.demo.entidades.Administrador;
import com.example.demo.repositorio.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    public Optional<Administrador> authenticate(String correo, String contrasena) {
        return administradorRepository.findByCorreoAndContrasena(correo, contrasena);
    }
}

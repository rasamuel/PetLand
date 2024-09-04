package com.example.demo.servicio;

import com.example.demo.entidades.Veterinario;
import com.example.demo.repositorio.VeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VeterinarioService {

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    public Optional<Veterinario> authenticate(String nombre, String contrasena) {
        return veterinarioRepository.findByNombreAndContrasena(nombre, contrasena);
    }
}

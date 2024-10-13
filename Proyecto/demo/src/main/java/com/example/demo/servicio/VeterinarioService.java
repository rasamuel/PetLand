package com.example.demo.servicio;

import com.example.demo.entidades.Veterinario;
import com.example.demo.repositorio.VeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeterinarioService {

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    // Método para autenticar veterinario
    public Optional<Veterinario> authenticate(String correo, String contrasena) {
        return veterinarioRepository.findByCorreoAndContrasena(correo, contrasena);
    }

    // Obtener todos los veterinarios
    public List<Veterinario> getAllVeterinarios() {
        return veterinarioRepository.findAll();
    }

    // Obtener un veterinario por ID
    public Optional<Veterinario> getVeterinarioById(Long id) {
        return veterinarioRepository.findById(id);
    }

    // Crear un nuevo veterinario
    public Veterinario createVeterinario(Veterinario veterinario) {
        return veterinarioRepository.save(veterinario);
    }

    // Actualizar un veterinario existente
    public Optional<Veterinario> updateVeterinario(Long id, Veterinario veterinarioDetails) {
        return veterinarioRepository.findById(id).map(veterinario -> {
            veterinario.setNombre(veterinarioDetails.getNombre());
            veterinario.setCorreo(veterinarioDetails.getCorreo());
            veterinario.setContrasena(veterinarioDetails.getContrasena());
            veterinario.setEspecialidad(veterinarioDetails.getEspecialidad());
            veterinario.setFoto(veterinarioDetails.getFoto());
            veterinario.setEstado(veterinarioDetails.getEstado());
            return veterinarioRepository.save(veterinario);
        });
    }

    // Eliminar un veterinario por ID
    public boolean deleteVeterinario(Long id) {
        if (veterinarioRepository.existsById(id)) {
            veterinarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Buscar veterinarios por nombre o especialidad
    public List<Veterinario> searchVeterinarios(String query) {
        return veterinarioRepository.findByNombreContainingOrEspecialidadContaining(query, query);
    }
}

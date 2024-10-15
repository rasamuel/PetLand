package com.example.demo.servicio;

import com.example.demo.entidades.Veterinario;
import com.example.demo.repositorio.VeterinarioRepository;
import com.example.demo.servicio.VeterinarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeterinarioServiceImpl implements VeterinarioService {

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    // Implementación del método para autenticar veterinario
    @Override
    public Optional<Veterinario> authenticate(String correo, String contrasena) {
        return veterinarioRepository.findByCorreoAndContrasena(correo, contrasena);
    }

    // Implementación del método para obtener todos los veterinarios
    @Override
    public List<Veterinario> getAllVeterinarios() {
        return veterinarioRepository.findAll();
    }

    // Implementación del método para obtener un veterinario por ID
    @Override
    public Optional<Veterinario> getVeterinarioById(Long id) {
        return veterinarioRepository.findById(id);
    }

    // Implementación del método para crear un veterinario
    @Override
    public Veterinario createVeterinario(Veterinario veterinario) {
        return veterinarioRepository.save(veterinario);
    }

    // Implementación del método para actualizar un veterinario
    @Override
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

    // Implementación del método para eliminar un veterinario por ID
    @Override
    public boolean deleteVeterinario(Long id) {
        if (veterinarioRepository.existsById(id)) {
            veterinarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Implementación del método para buscar veterinarios por nombre o especialidad
    @Override
    public List<Veterinario> searchVeterinarios(String query) {
        return veterinarioRepository.findByNombreContainingOrEspecialidadContaining(query, query);
    }

    public Optional<Veterinario> findById(Long id) {
        return veterinarioRepository.findById(id);
    }
}

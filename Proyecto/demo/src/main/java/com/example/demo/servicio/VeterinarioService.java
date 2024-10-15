package com.example.demo.servicio;

import com.example.demo.entidades.Veterinario;

import java.util.List;
import java.util.Optional;

public interface VeterinarioService {

    // Método para autenticar veterinario
    Optional<Veterinario> authenticate(String correo, String contrasena);

    // Obtener todos los veterinarios
    List<Veterinario> getAllVeterinarios();

    // Obtener un veterinario por ID
    Optional<Veterinario> getVeterinarioById(Long id);

    // Crear un nuevo veterinario
    Veterinario createVeterinario(Veterinario veterinario);

    // Actualizar un veterinario existente
    Optional<Veterinario> updateVeterinario(Long id, Veterinario veterinarioDetails);

    // Eliminar un veterinario por ID
    boolean deleteVeterinario(Long id);

    // Buscar veterinarios por nombre o especialidad
    List<Veterinario> searchVeterinarios(String query);

    public Optional<Veterinario> findById(Long id);
}

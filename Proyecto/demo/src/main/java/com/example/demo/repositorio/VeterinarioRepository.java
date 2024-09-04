package com.example.demo.repositorio;

import com.example.demo.entidades.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {

    List<Veterinario> findByEspecialidad(String especialidad);

    List<Veterinario> findByNombre(String nombre);

    List<Veterinario> findByEspecialidadAndNombre(String especialidad, String nombre);

    List<Veterinario> findByNombreContaining(String keyword);

    List<Veterinario> findByEspecialidadOrNombre(String especialidad, String nombre);

    List<Veterinario> findByNombreStartingWith(String prefix);
    
    Optional<Veterinario> findByNombreAndContrasena(String nombre, String contrasena);
    
}

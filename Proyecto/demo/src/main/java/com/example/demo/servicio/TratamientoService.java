package com.example.demo.servicio;

import com.example.demo.entidades.Tratamiento;

import java.util.List;

public interface TratamientoService {

    // Crear un nuevo tratamiento
    Tratamiento createTratamiento(Tratamiento tratamiento);

    // Obtener todos los tratamientos realizados por un veterinario
    List<Tratamiento> getTratamientosByVeterinario(Long veterinarioId);

    List<Tratamiento> getTratamientosPorMascota(Long mascotaId);
}

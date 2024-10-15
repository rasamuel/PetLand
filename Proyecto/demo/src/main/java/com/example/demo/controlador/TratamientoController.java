package com.example.demo.controlador;

import com.example.demo.entidades.Tratamiento;
import com.example.demo.entidades.Veterinario;
import com.example.demo.servicio.TratamientoService;
import com.example.demo.servicio.VeterinarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tratamientos")
public class TratamientoController {

    @Autowired
    private TratamientoService tratamientoService;

    @Autowired
    private VeterinarioService veterinarioService;

    // Crear un nuevo tratamiento
@PostMapping()
public ResponseEntity<?> crearTratamiento(@RequestBody Tratamiento tratamiento) {
    System.out.println("Recibido tratamiento: " + tratamiento); // Mensaje inicial para verificar el objeto recibido

    // Verificar si el medicamento, veterinario o mascota son nulos
    if (tratamiento.getMedicamento() == null) {
        System.out.println("Error: Medicamento es nulo.");
        return ResponseEntity.badRequest().body("Datos faltantes: Medicamento es nulo");
    }
    
    if (tratamiento.getMascota() == null) {
        System.out.println("Error: Mascota es nula.");
        return ResponseEntity.badRequest().body("Datos faltantes: Mascota es nula");
    }

    if (tratamiento.getVeterinario() == null) {
        System.out.println("Error: Veterinario es nulo.");
        return ResponseEntity.badRequest().body("Datos faltantes: Veterinario es nulo");
    }

    // Verificar si el veterinario existe
    Long veterinarioId = tratamiento.getVeterinario().getId(); // Obtener el ID del veterinario del objeto tratamiento
    System.out.println("ID del veterinario: " + veterinarioId); // Mostrar el ID del veterinario

    Optional<Veterinario> veterinarioOptional = veterinarioService.findById(veterinarioId);
    if (veterinarioOptional.isEmpty()) {
        System.out.println("Error: Veterinario no encontrado con ID: " + veterinarioId);
        return ResponseEntity.badRequest().body("Veterinario no encontrado");
    }

    // Asignar el veterinario encontrado al tratamiento
    tratamiento.setVeterinario(veterinarioOptional.get());
    System.out.println("Veterinario asignado: " + veterinarioOptional.get()); // Verificar el veterinario asignado

    // Crear el tratamiento
    Tratamiento nuevoTratamiento = tratamientoService.createTratamiento(tratamiento);
    System.out.println("Tratamiento creado exitosamente: " + nuevoTratamiento); // Mensaje de éxito

    return ResponseEntity.ok(nuevoTratamiento);
}

    // Obtener todos los tratamientos de un veterinario específico
    @GetMapping("/tratamiento/veterinario/{veterinarioId}")
    public List<Tratamiento> getTratamientosByVeterinario(@PathVariable Long veterinarioId) {
        return tratamientoService.getTratamientosByVeterinario(veterinarioId);
    }

    @GetMapping("/tratamiento/mascota/{mascotaId}")
    public List<Tratamiento> getTratamientosPorMascota(@PathVariable Long mascotaId) {
    return tratamientoService.getTratamientosPorMascota(mascotaId); // Llama al servicio para obtener los tratamientos
}

}

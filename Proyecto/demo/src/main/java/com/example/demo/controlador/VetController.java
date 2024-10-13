package com.example.demo.controlador;

import com.example.demo.entidades.Veterinario;
import com.example.demo.servicio.VeterinarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/veterinarios")
@CrossOrigin(origins = "http://localhost:4200") // Permite solicitudes desde el frontend
public class VetController {

    @Autowired
    private VeterinarioService veterinarioService;

    // Obtener lista de todos los veterinarios
    @GetMapping
    public List<Veterinario> getAllVeterinarios() {
        return veterinarioService.getAllVeterinarios();
    }

    // Obtener un veterinario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Veterinario> getVeterinarioById(@PathVariable Long id) {
        Optional<Veterinario> veterinario = veterinarioService.getVeterinarioById(id);
        return veterinario.map(ResponseEntity::ok)  // Si está presente, devolverlo con status 200
                          .orElseGet(() -> ResponseEntity.notFound().build());  // Si no, devolver 404
    }

    // Crear un nuevo veterinario
    @PostMapping
    public Veterinario createVeterinario(@RequestBody Veterinario veterinario) {
        return veterinarioService.createVeterinario(veterinario);
    }

    // Actualizar un veterinario existente
    @PutMapping("/{id}")
    public ResponseEntity<Veterinario> updateVeterinario(@PathVariable Long id, @RequestBody Veterinario veterinarioDetails) {
        Optional<Veterinario> updatedVeterinario = veterinarioService.updateVeterinario(id, veterinarioDetails);
        return updatedVeterinario.map(ResponseEntity::ok)
                                 .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar un veterinario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeterinario(@PathVariable Long id) {
        boolean deleted = veterinarioService.deleteVeterinario(id);
        if (deleted) {
            return ResponseEntity.noContent().build();  // Si se eliminó, devolver 204 No Content
        } else {
            return ResponseEntity.notFound().build();   // Si no se encontró, devolver 404
        }
    }

    // Buscar veterinarios por nombre o especialidad
    @GetMapping("/buscar")
    public List<Veterinario> searchVeterinarios(@RequestParam String query) {
        return veterinarioService.searchVeterinarios(query);
    }
}

package com.example.demo.controlador;

import com.example.demo.entidades.Medicamento;
import com.example.demo.servicio.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medicamentos")
@CrossOrigin(origins = "http://localhost:4200")
public class MedicamentoController {

    @Autowired
    private MedicamentoService medicamentoService;

    // Obtener todos los medicamentos
    @GetMapping
    public List<Medicamento> getAllMedicamentos() {
        return medicamentoService.getAllMedicamentos();
    }

    // Obtener un medicamento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Medicamento> getMedicamentoById(@PathVariable Long id) {
        Optional<Medicamento> medicamento = medicamentoService.getMedicamentoById(id);
        return medicamento.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo medicamento
    @PostMapping
    public Medicamento createMedicamento(@RequestBody Medicamento medicamento) {
        return medicamentoService.createMedicamento(medicamento);
    }

    // Actualizar un medicamento existente
    @PutMapping("/{id}")
    public ResponseEntity<Medicamento> updateMedicamento(@PathVariable Long id, @RequestBody Medicamento medicamentoDetails) {
        Optional<Medicamento> updatedMedicamento = medicamentoService.updateMedicamento(id, medicamentoDetails);
        return updatedMedicamento.map(ResponseEntity::ok)
                                 .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar un medicamento por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicamento(@PathVariable Long id) {
        boolean deleted = medicamentoService.deleteMedicamento(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Buscar medicamentos por nombre (opcional)
    @GetMapping("/buscar")
    public List<Medicamento> searchMedicamentos(@RequestParam String query) {
        return medicamentoService.searchMedicamentos(query);
    }
}

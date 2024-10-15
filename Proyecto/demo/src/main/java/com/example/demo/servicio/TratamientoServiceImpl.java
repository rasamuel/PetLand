package com.example.demo.servicio;

import com.example.demo.entidades.Medicamento;
import com.example.demo.entidades.Tratamiento;
import com.example.demo.repositorio.MedicamentoRepository;
import com.example.demo.repositorio.TratamientoRepository;
import com.example.demo.servicio.TratamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TratamientoServiceImpl implements TratamientoService {

    @Autowired
    private TratamientoRepository tratamientoRepository;

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    // Implementación del método para crear un tratamiento
    @Override
    public Tratamiento createTratamiento(Tratamiento tratamiento) {
        Medicamento medicamento = tratamiento.getMedicamento();
        // Verifica si hay suficientes unidades disponibles
        if (medicamento.getUnidadesDisponibles() >= tratamiento.getCantidad()) {
            // Actualiza las unidades disponibles y vendidas
            medicamento.setUnidadesDisponibles(medicamento.getUnidadesDisponibles() - tratamiento.getCantidad());
            medicamento.setUnidadesVendidas(medicamento.getUnidadesVendidas() + tratamiento.getCantidad());
            medicamentoRepository.save(medicamento);  // Guarda el medicamento actualizado
            return tratamientoRepository.save(tratamiento);  // Guarda el tratamiento
        } else {
            throw new IllegalArgumentException("No hay suficientes unidades disponibles.");
        }
    }

    // Implementación del método para obtener tratamientos por veterinario
    @Override
    public List<Tratamiento> getTratamientosByVeterinario(Long veterinarioId) {
        return tratamientoRepository.findByVeterinarioId(veterinarioId);
    }
}

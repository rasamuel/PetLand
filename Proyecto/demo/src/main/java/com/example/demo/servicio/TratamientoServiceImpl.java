package com.example.demo.servicio;

import com.example.demo.entidades.Medicamento;
import com.example.demo.entidades.Tratamiento;
import com.example.demo.repositorio.MedicamentoRepository;
import com.example.demo.repositorio.TratamientoRepository;
import com.example.demo.servicio.TratamientoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.time.ZoneId;
import java.util.HashMap; // Para usar HashMap
import java.util.Map; // Para usar Map
import java.util.ArrayList; // Para crear una lista de tratamientos resumidos

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
    @Override
    public List<Tratamiento> getTratamientosPorMascota(Long mascotaId) {
        // Aquí deberías implementar la lógica para obtener los tratamientos de la mascota
        return tratamientoRepository.findByMascotaId(mascotaId); // Suponiendo que tienes un repositorio con este método
    }

    @Override
    public Long contarTotalTratamientosUltimoMes() {
        LocalDate startDate = LocalDate.now().minusMonths(1);
        LocalDate endDate = LocalDate.now();
    
        // Convertir LocalDate a Date
        Date start = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(endDate.atTime(23, 59, 59).atZone(ZoneId.systemDefault()).toInstant());
    
        return tratamientoRepository.countByFechaBetween(start, end);
    }

@Override
public List<Tratamiento> contarTratamientosPorMedicamentoUltimoMes() {
    LocalDate unMesAtras = LocalDate.now().minusMonths(1);
    LocalDate ahora = LocalDate.now();

    Date start = Date.from(unMesAtras.atStartOfDay(ZoneId.systemDefault()).toInstant());
    Date end = Date.from(ahora.atTime(23, 59, 59).atZone(ZoneId.systemDefault()).toInstant());

    List<Tratamiento> tratamientos = tratamientoRepository.findByFechaBetween(start, end);

    // Agrupar y sumar cantidades por medicamento
    Map<String, Integer> resumen = new HashMap<>();
    for (Tratamiento tratamiento : tratamientos) {
        String medicamentoNombre = tratamiento.getMedicamento().getNombre();
        int cantidad = tratamiento.getCantidad();
        resumen.put(medicamentoNombre, resumen.getOrDefault(medicamentoNombre, 0) + cantidad);
    }

    // Convertir el mapa a una lista
    List<Tratamiento> tratamientosResumen = new ArrayList<>();
    for (Map.Entry<String, Integer> entry : resumen.entrySet()) {
        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setMedicamento(new Medicamento(entry.getKey())); // Suponiendo que tienes un constructor en Medicamento
        tratamiento.setCantidad(entry.getValue());
        tratamientosResumen.add(tratamiento);
    }

    return tratamientosResumen;
}

}

package com.example.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.entidades.Medicamento;
import com.example.demo.entidades.Tratamiento;
import com.example.demo.repositorio.MedicamentoRepository;
import com.example.demo.repositorio.TratamientoRepository;
import com.example.demo.servicio.TratamientoServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TratamientoServiceMockTests {

    @Mock
    private TratamientoRepository tratamientoRepository;

    @Mock
    private MedicamentoRepository medicamentoRepository;

    @InjectMocks
    private TratamientoServiceImpl tratamientoService;

    private Medicamento medicamento;
    private Tratamiento tratamiento; // Definir como atributo de clase

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks

        // Configura un medicamento para las pruebas
        medicamento = new Medicamento();
        medicamento.setNombre("Acolan");
        medicamento.setUnidadesDisponibles(10);
        medicamento.setUnidadesVendidas(0);

        // Configura un tratamiento para las pruebas
        tratamiento = new Tratamiento();
        tratamiento.setMedicamento(medicamento);
        tratamiento.setCantidad(2);
        tratamiento.setFecha(new Date());
    }

    @Test
    public void testCreateTratamientoSuccess() {
        // Simula el comportamiento del repositorio
        when(medicamentoRepository.save(any(Medicamento.class))).thenReturn(medicamento);
        when(tratamientoRepository.save(any(Tratamiento.class))).thenReturn(tratamiento);

        // Ejecutar el método
        Tratamiento creado = tratamientoService.createTratamiento(tratamiento);

        // Validaciones
        assertThat(creado).isNotNull();
        assertThat(creado.getCantidad()).isEqualTo(2);
        assertThat(medicamento.getUnidadesDisponibles()).isEqualTo(8); // Verifica que las unidades se hayan actualizado
        verify(medicamentoRepository, times(1)).save(medicamento); // Verifica que se llame al método save del medicamento
        verify(tratamientoRepository, times(1)).save(tratamiento); // Verifica que se llame al método save del tratamiento
    }

    @Test
    public void testCreateTratamientoInsufficientStock() {
        // Preparar el tratamiento
        tratamiento.setCantidad(12); // Mayor que el stock disponible

        // Verifica que se lanza una excepción al intentar crear el tratamiento
        assertThrows(IllegalArgumentException.class, () -> {
            tratamientoService.createTratamiento(tratamiento);
        });

        // No debe guardar el medicamento ni el tratamiento
        verify(medicamentoRepository, never()).save(any(Medicamento.class));
        verify(tratamientoRepository, never()).save(any(Tratamiento.class));
    }

    @Test
    public void testGetTratamientosByVeterinario() {
        // Configura el comportamiento del mock
        List<Tratamiento> tratamientosMock = new ArrayList<>();
        tratamientosMock.add(tratamiento);

        when(tratamientoRepository.findByVeterinarioId(anyLong())).thenReturn(tratamientosMock);

        // Ejecutar el método
        List<Tratamiento> tratamientos = tratamientoService.getTratamientosByVeterinario(1L);

        // Validaciones
        assertThat(tratamientos).isNotEmpty();
        assertThat(tratamientos.size()).isEqualTo(1);
        verify(tratamientoRepository, times(1)).findByVeterinarioId(1L);
    }

    @Test
    public void testGetTratamientosPorMascota() {
        // Configura el comportamiento del mock
        List<Tratamiento> tratamientosMock = new ArrayList<>();
        tratamientosMock.add(tratamiento);

        when(tratamientoRepository.findByMascotaId(anyLong())).thenReturn(tratamientosMock);

        // Ejecutar el método
        List<Tratamiento> tratamientos = tratamientoService.getTratamientosPorMascota(1L);

        // Validaciones
        assertThat(tratamientos).isNotEmpty();
        assertThat(tratamientos.size()).isEqualTo(1);
        verify(tratamientoRepository, times(1)).findByMascotaId(1L);
    }

    @Test
    public void testContarTotalTratamientosUltimoMes() {
        // Configura el comportamiento del mock
        when(tratamientoRepository.countByFechaBetween(any(Date.class), any(Date.class))).thenReturn(5L);

        // Ejecutar el método
        Long total = tratamientoService.contarTotalTratamientosUltimoMes();

        // Validaciones
        assertThat(total).isEqualTo(5);
        verify(tratamientoRepository, times(1)).countByFechaBetween(any(Date.class), any(Date.class));
    }
}

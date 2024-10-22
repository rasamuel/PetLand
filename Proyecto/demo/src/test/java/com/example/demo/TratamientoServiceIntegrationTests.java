package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.entidades.Medicamento;
import com.example.demo.entidades.Pet;
import com.example.demo.entidades.Tratamiento;
import com.example.demo.entidades.Veterinario;
import com.example.demo.repositorio.MedicamentoRepository;
import com.example.demo.repositorio.TratamientoRepository;
import com.example.demo.servicio.TratamientoServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@SpringBootTest /
@Transactional // Asegura que se haga rollback después de cada prueba
public class TratamientoServiceIntegrationTests {

    @Autowired
    private TratamientoServiceImpl tratamientoService;

    @Autowired
    private TratamientoRepository tratamientoRepository;

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    private Medicamento medicamento;
    private Pet mascota;
    private Veterinario veterinario;

    @BeforeEach
    public void setUp() {
        // Configura el medicamento
        medicamento = new Medicamento();
        medicamento.setNombre("Paracetamol");
        medicamento.setUnidadesDisponibles(20);
        medicamento.setUnidadesVendidas(0);
        medicamentoRepository.save(medicamento);

        // Configura la mascota
        mascota = new Pet();
        mascota.setNombre("Fido");
        mascota.setRaza("Labrador");
        mascota.setEnfermedad("Ninguna");
        mascota.setEstado(true);
        mascota.setEdad(5);
        mascota.setPeso(30.5);

        // Configura el veterinario
        veterinario = new Veterinario();
        veterinario.setNombre("Dr. Smith");
    }

    @Test
    public void testCreateTratamiento() {
        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setMedicamento(medicamento);
        tratamiento.setCantidad(5);
        tratamiento.setFecha(new Date());
        tratamiento.setMascota(mascota);
        tratamiento.setVeterinario(veterinario);

        // Crear tratamiento
        Tratamiento creado = tratamientoService.createTratamiento(tratamiento);

        assertThat(creado).isNotNull();
        assertThat(creado.getCantidad()).isEqualTo(5);
        assertThat(medicamento.getUnidadesDisponibles()).isEqualTo(15); // Verifica que las unidades se hayan actualizado
    }

    @Test
    public void testGetTratamientosByVeterinario() {
        // Primero, crea un tratamiento
        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setMedicamento(medicamento);
        tratamiento.setCantidad(5);
        tratamiento.setFecha(new Date());
        tratamiento.setVeterinario(veterinario);
        tratamientoRepository.save(tratamiento);

        // Obtener tratamientos por veterinario
        List<Tratamiento> tratamientos = tratamientoService.getTratamientosByVeterinario(veterinario.getId());

        assertThat(tratamientos).isNotEmpty();
        assertThat(tratamientos.size()).isEqualTo(1);
        assertThat(tratamientos.get(0).getMedicamento().getNombre()).isEqualTo("Paracetamol");
    }

    @Test
    public void testGetTratamientosPorMascota() {
        // Simula la creación de un tratamiento para una mascota
        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setMedicamento(medicamento);
        tratamiento.setCantidad(3);
        tratamiento.setFecha(new Date());
        tratamiento.setMascota(mascota);
        tratamientoRepository.save(tratamiento);

        List<Tratamiento> tratamientos = tratamientoService.getTratamientosPorMascota(mascota.getId());

        assertThat(tratamientos).isNotEmpty();
        assertThat(tratamientos.size()).isEqualTo(1);
    }

    @Test
    public void testContarTotalTratamientosUltimoMes() {
        // Simula la creación de un tratamiento en el último mes
        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setMedicamento(medicamento);
        tratamiento.setCantidad(2);
        tratamiento.setFecha(new Date());
        tratamientoRepository.save(tratamiento);

        Long total = tratamientoService.contarTotalTratamientosUltimoMes();

        assertThat(total).isGreaterThan(0); // Verifica que haya tratamientos en el último mes
    }

    @Test
    public void testContarTratamientosPorMedicamentoUltimoMes() {
        // Simula la creación de tratamientos en el último mes
        Tratamiento tratamiento1 = new Tratamiento();
        tratamiento1.setMedicamento(medicamento);
        tratamiento1.setCantidad(2);
        tratamiento1.setFecha(new Date());
        tratamientoRepository.save(tratamiento1);

        Tratamiento tratamiento2 = new Tratamiento();
        tratamiento2.setMedicamento(medicamento);
        tratamiento2.setCantidad(3);
        tratamiento2.setFecha(new Date());
        tratamientoRepository.save(tratamiento2);

        List<Tratamiento> tratamientosResumen = tratamientoService.contarTratamientosPorMedicamentoUltimoMes();

        assertThat(tratamientosResumen).isNotEmpty();
        assertThat(tratamientosResumen.size()).isEqualTo(1); // Verifica que haya un resumen para el medicamento
        assertThat(tratamientosResumen.get(0).getCantidad()).isEqualTo(5); // Verifica que las cantidades se sumen
    }
}

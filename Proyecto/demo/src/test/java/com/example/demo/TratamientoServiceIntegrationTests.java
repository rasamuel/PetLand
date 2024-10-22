package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.entidades.Medicamento;
import com.example.demo.entidades.Pet;
import com.example.demo.entidades.Tratamiento;
import com.example.demo.entidades.Veterinario;
import com.example.demo.repositorio.MedicamentoRepository;
import com.example.demo.repositorio.PetRepository;
import com.example.demo.repositorio.TratamientoRepository;
import com.example.demo.repositorio.VeterinarioRepository;
import com.example.demo.servicio.TratamientoServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@SpringBootTest
@Transactional // Asegura que se haga rollback después de cada prueba
public class TratamientoServiceIntegrationTests {

    @Autowired
    private TratamientoServiceImpl tratamientoService;

    @Autowired
    private TratamientoRepository tratamientoRepository;

    @Autowired
    private MedicamentoRepository medicamentoRepository;
    
    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Autowired
    private PetRepository petRepository;


    private Medicamento medicamento;
    private Pet mascota;
    private Veterinario veterinario;

    @BeforeEach
    public void setUp() {
        // Configura el medicamento
        medicamento = new Medicamento();
        medicamento.setNombre("Chontaduro");
        medicamento.setUnidadesDisponibles(20);
        medicamento.setUnidadesVendidas(0);
        medicamentoRepository.save(medicamento); // Asegúrate de guardar el medicamento

        // Configura la mascota
        mascota = new Pet();
        mascota.setNombre("Fido");
        mascota.setRaza("Labrador");
        mascota.setEnfermedad("Ninguna");
        mascota.setEstado(true);
        mascota.setEdad(5);
        mascota.setPeso(30.5);
        // Guarda la mascota si es necesario

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
        tratamiento.setVeterinario(veterinario); // Asegúrate de que el veterinario está guardado

        // Crear tratamiento
        Tratamiento creado = tratamientoService.createTratamiento(tratamiento);

        assertThat(creado).isNotNull();
        assertThat(creado.getCantidad()).isEqualTo(5);
        assertThat(medicamento.getUnidadesDisponibles()).isEqualTo(15); // Verifica que las unidades se hayan actualizado
    }

    @Test
    public void testGetTratamientosByVeterinario() {
        // Primero, crea y guarda el veterinario
        Veterinario savedVeterinario = new Veterinario();
        savedVeterinario.setNombre("Dr. Smith");
        // Guarda el veterinario en el repositorio
        veterinarioRepository.save(savedVeterinario);

        // Primero, crea un tratamiento
        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setMedicamento(medicamento);
        tratamiento.setCantidad(5);
        tratamiento.setFecha(new Date());
        tratamiento.setVeterinario(savedVeterinario); // Usa el veterinario guardado
        tratamientoRepository.save(tratamiento);

        // Obtener tratamientos por veterinario
        List<Tratamiento> tratamientos = tratamientoService.getTratamientosByVeterinario(savedVeterinario.getId());

        assertThat(tratamientos).isNotEmpty();
        assertThat(tratamientos.size()).isEqualTo(1);
        assertThat(tratamientos.get(0).getMedicamento().getNombre()).isEqualTo("Chontaduro");
    }

    @Test
    public void testGetTratamientosPorMascota() {
        mascota.setId(null);
        petRepository.save(mascota); // Guarda la mascota en el repositorio
    
        // Simula la creación de un tratamiento para la mascota
        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setMedicamento(medicamento);
        tratamiento.setCantidad(3);
        tratamiento.setFecha(new Date());
        tratamiento.setMascota(mascota); // Asocia la mascota al tratamiento
        tratamientoRepository.save(tratamiento); // Guarda el tratamiento en el repositorio
    
        // Obtener tratamientos por mascota
        List<Tratamiento> tratamientos = tratamientoService.getTratamientosPorMascota(mascota.getId());
    
        // Validaciones
        assertThat(tratamientos).isNotEmpty(); // Verifica que la lista de tratamientos no esté vacía
        assertThat(tratamientos.size()).isEqualTo(1); // Verifica que haya exactamente un tratamiento
        assertThat(tratamientos.get(0).getMascota().getId()).isEqualTo(mascota.getId()); // Verifica que el tratamiento esté asociado a la mascota correcta
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
    
        // Llamar al método que cuenta tratamientos por medicamento en el último mes
        List<Tratamiento> tratamientosResumen = tratamientoService.contarTratamientosPorMedicamentoUltimoMes();
    
        // Validaciones
        assertThat(tratamientosResumen).isNotEmpty(); // Verifica que la lista de tratamientos no esté vacía

        assertThat(tratamientosResumen.size()).isEqualTo(10); // Verifica que haya un resumen para el medicamento
        assertThat(tratamientosResumen.get(0).getCantidad()).isEqualTo(1); // Verifica que las cantidades se sumen
        assertThat(tratamientosResumen.get(0).getMedicamento().getNombre()).isEqualTo("ALAMYCIN"); // Verifica el nombre del medicamento
    }
    
    

}

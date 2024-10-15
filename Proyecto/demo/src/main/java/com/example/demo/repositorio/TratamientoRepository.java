package com.example.demo.repositorio;

import com.example.demo.entidades.Tratamiento;
import com.example.demo.entidades.Medicamento;
import com.example.demo.entidades.Pet;
import com.example.demo.entidades.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.sql.Timestamp;

@Repository
public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {

    List<Tratamiento> findByFecha(Date fecha);

    List<Tratamiento> findByFechaBetween(Date startDate, Date endDate);

    List<Tratamiento> findByCantidad(int cantidad);

    List<Tratamiento> findByMedicamento(Medicamento medicamento);

    List<Tratamiento> findByMascota(Pet mascota);

    List<Tratamiento> findByVeterinario(Veterinario veterinario);

    List<Tratamiento> findByMedicamentoAndFecha(Medicamento medicamento, Date fecha);

    List<Tratamiento> findByMascotaAndFechaBetween(Pet mascota, Date startDate, Date endDate);

    List<Tratamiento> findByVeterinarioAndCantidad(Veterinario veterinario, int cantidad);

    List<Tratamiento> findByMedicamentoAndFechaBetween(Medicamento medicamento, Date startDate, Date endDate);

    List<Tratamiento> findByVeterinarioId(Long veterinarioId);

    List<Tratamiento> findByMascotaId(Long mascotaId);

    // Contar total de tratamientos administrados en el último mes
    @Query("SELECT COUNT(t) FROM Tratamiento t WHERE t.fecha >= :fechaInicio")
    Long countByFechaGreaterThanEqual(LocalDate fechaInicio);
    
    // Obtener tratamientos administrados por medicamento en el último mes
    @Query("SELECT t FROM Tratamiento t WHERE t.fecha >= :fechaInicio")
    List<Tratamiento> findByFechaGreaterThanEqual(LocalDate fechaInicio);

    @Query("SELECT COUNT(t) FROM Tratamiento t WHERE t.fecha BETWEEN :start AND :end")
    Long countByFechaBetween(@Param("start") Date start, @Param("end") Date end);

    @Query("SELECT m.nombre, COUNT(t) FROM Tratamiento t JOIN t.medicamento m WHERE t.fecha BETWEEN :start AND :end GROUP BY m.nombre")
    List<Object[]> countTratamientosPorMedicamento(@Param("start") Date start, @Param("end") Date end);

}

package com.example.demo.repositorio;

import com.example.demo.entidades.Tratamiento;
import com.example.demo.entidades.Medicamento;
import com.example.demo.entidades.Pet;
import com.example.demo.entidades.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

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
}

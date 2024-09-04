package com.example.demo.repositorio;

import com.example.demo.entidades.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

    Optional<Medicamento> findByNombre(String nombre);

    List<Medicamento> findByNombreContaining(String keyword);

    List<Medicamento> findByNombreIgnoreCase(String nombre);

    List<Medicamento> findByPrecioVentaGreaterThanEqual(float precioVenta);

    List<Medicamento> findByPrecioCompraBetween(float minPrecioCompra, float maxPrecioCompra);

    List<Medicamento> findByUnidadesDisponiblesLessThan(int umbral);

    List<Medicamento> findByUnidadesVendidasGreaterThan(int minUnidadesVendidas);

    List<Medicamento> findByUnidadesDisponiblesBetween(int minUnidadesDisponibles, int maxUnidadesDisponibles);

    List<Medicamento> findByNombreContainingOrPrecioVentaBetween(String nombreKeyword, float minPrecioVenta, float maxPrecioVenta);
}

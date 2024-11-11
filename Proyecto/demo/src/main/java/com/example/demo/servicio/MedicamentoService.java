package com.example.demo.servicio;

import com.example.demo.dto.MedicamentoDTO;
import com.example.demo.entidades.Medicamento;

import java.util.List;
import java.util.Optional;

public interface MedicamentoService {
    List<MedicamentoDTO> getAllMedicamentos();
    Optional<MedicamentoDTO> getMedicamentoById(Long id);
    MedicamentoDTO createMedicamento(Medicamento medicamento);
    Optional<MedicamentoDTO> updateMedicamento(Long id, Medicamento medicamentoDetails);
    boolean deleteMedicamento(Long id);
    List<MedicamentoDTO> searchMedicamentos(String query);
    Double calcularVentasTotales();
    Double calcularGananciasTotales();
    List<MedicamentoDTO> obtenerTopMedicamentos();
}

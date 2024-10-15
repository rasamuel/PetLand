package com.example.demo.servicio;

import com.example.demo.entidades.Medicamento;

import java.util.List;
import java.util.Optional;

public interface MedicamentoService {
    List<Medicamento> getAllMedicamentos();
    Optional<Medicamento> getMedicamentoById(Long id);
    Medicamento createMedicamento(Medicamento medicamento);
    Optional<Medicamento> updateMedicamento(Long id, Medicamento medicamentoDetails);
    boolean deleteMedicamento(Long id);
    List<Medicamento> searchMedicamentos(String query);
}

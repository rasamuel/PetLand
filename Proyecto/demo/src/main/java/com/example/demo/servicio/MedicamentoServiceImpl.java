package com.example.demo.servicio;

import com.example.demo.entidades.Medicamento;
import com.example.demo.repositorio.MedicamentoRepository;
import com.example.demo.servicio.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Override
    public List<Medicamento> getAllMedicamentos() {
        return medicamentoRepository.findAll();
    }

    @Override
    public Optional<Medicamento> getMedicamentoById(Long id) {
        return medicamentoRepository.findById(id);
    }

    @Override
    public Medicamento createMedicamento(Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }

    @Override
    public Optional<Medicamento> updateMedicamento(Long id, Medicamento medicamentoDetails) {
        return medicamentoRepository.findById(id).map(medicamento -> {
            medicamento.setNombre(medicamentoDetails.getNombre());
            medicamento.setPrecioCompra(medicamentoDetails.getPrecioCompra());
            medicamento.setPrecioVenta(medicamentoDetails.getPrecioVenta());
            medicamento.setUnidadesDisponibles(medicamentoDetails.getUnidadesDisponibles());
            medicamento.setUnidadesVendidas(medicamentoDetails.getUnidadesVendidas());
            return medicamentoRepository.save(medicamento);
        });
    }

    @Override
    public boolean deleteMedicamento(Long id) {
        if (medicamentoRepository.existsById(id)) {
            medicamentoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Medicamento> searchMedicamentos(String query) {
        return medicamentoRepository.findByNombreContaining(query);
    }
}

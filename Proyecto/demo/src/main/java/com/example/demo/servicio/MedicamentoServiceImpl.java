package com.example.demo.servicio;

import com.example.demo.dto.MedicamentoDTO;
import com.example.demo.entidades.Medicamento;
import com.example.demo.repositorio.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Override
    public List<MedicamentoDTO> getAllMedicamentos() {
        return medicamentoRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MedicamentoDTO> getMedicamentoById(Long id) {
        return medicamentoRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public MedicamentoDTO createMedicamento(Medicamento medicamento) {
        Medicamento savedMedicamento = medicamentoRepository.save(medicamento);
        return convertToDTO(savedMedicamento);
    }

    @Override
    public Optional<MedicamentoDTO> updateMedicamento(Long id, Medicamento medicamentoDetails) {
        return medicamentoRepository.findById(id).map(medicamento -> {
            medicamento.setNombre(medicamentoDetails.getNombre());
            medicamento.setPrecioVenta(medicamentoDetails.getPrecioVenta());
            medicamento.setUnidadesDisponibles(medicamentoDetails.getUnidadesDisponibles());
            medicamento.setUnidadesVendidas(medicamentoDetails.getUnidadesVendidas());
            return convertToDTO(medicamentoRepository.save(medicamento));
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
    public List<MedicamentoDTO> searchMedicamentos(String query) {
        return medicamentoRepository.findByNombreContaining(query)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Double calcularVentasTotales() {
        return medicamentoRepository.sumVentasTotales();
    }

    @Override
    public Double calcularGananciasTotales() {
        return medicamentoRepository.sumGananciasTotales();
    }

    @Override
    public List<MedicamentoDTO> obtenerTopMedicamentos() {
        return medicamentoRepository.findTop3ByOrderByUnidadesVendidasDesc()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private MedicamentoDTO convertToDTO(Medicamento medicamento) {
        return new MedicamentoDTO(
                medicamento.getId(),
                medicamento.getNombre(),
                medicamento.getPrecioVenta(),
                medicamento.getUnidadesDisponibles(),
                medicamento.getUnidadesVendidas()
        );
    }
}

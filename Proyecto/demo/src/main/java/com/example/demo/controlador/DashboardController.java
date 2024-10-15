package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entidades.Medicamento;
import com.example.demo.entidades.Tratamiento;
import com.example.demo.servicio.MedicamentoService;
import com.example.demo.servicio.TratamientoService;
import com.example.demo.servicio.VeterinarioService;
import com.example.demo.servicio.PetService; 

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private VeterinarioService veterinarioService;

    @Autowired 
    private TratamientoService tratamientoService;

    @Autowired
    private PetService petService; 

    @Autowired
    private MedicamentoService medicamentoService; 

    // Obtiene la cantidad total de tratamientos administrados en el último mes
    @GetMapping("/total-tratamientos-ultimo-mes")
    public Long getTotalTratamientosUltimoMes() {
        return tratamientoService.contarTotalTratamientosUltimoMes();
    }
    
    // Obtiene la lista de tratamientos administrados por medicamento en el último mes
    @GetMapping("/tratamientos-por-medicamento")
    public List<Tratamiento> getTratamientosPorMedicamentoUltimoMes() {
        return tratamientoService.contarTratamientosPorMedicamentoUltimoMes();
    }

    // Obtiene la cantidad de veterinarios activos en la plataforma
    @GetMapping("/veterinarios-activos")
    public Long getVeterinariosActivos() {
        return veterinarioService.contarVeterinariosActivos();
    }

    // Obtiene la cantidad de veterinarios inactivos en la plataforma
    @GetMapping("/veterinarios-inactivos")
    public Long getVeterinariosInactivos() {
        return veterinarioService.contarVeterinariosInactivos();
    }

    // Obtiene la cantidad total de mascotas en la veterinaria
    @GetMapping("/total-mascotas")
    public Long getTotalMascotas() {
        return petService.contarTotalMascotas();
    }

    // Obtiene la cantidad de mascotas activas en la veterinaria
    @GetMapping("/mascotas-activas")
    public Long getMascotasActivas() {
        return petService.contarMascotasActivas();
    }

    // Obtiene las ventas totales de la veterinaria
    @GetMapping("/ventas-totales")
    public Double getVentasTotales() {
        return medicamentoService.calcularVentasTotales();
    }

    // Obtiene las ganancias totales de la veterinaria
    @GetMapping("/ganancias-totales")
    public Double getGananciasTotales() {
        return medicamentoService.calcularGananciasTotales();
    }

    // Obtiene el top de medicamentos con más unidades vendidas
    @GetMapping("/top-medicamentos")
    public List<Medicamento> getTopMedicamentos() {
        return medicamentoService.obtenerTopMedicamentos();
    }
}

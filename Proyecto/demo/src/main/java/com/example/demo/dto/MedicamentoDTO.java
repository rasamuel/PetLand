// MedicamentoDTO.java
package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicamentoDTO {
    private Long id;
    private String nombre;
    private float precioVenta;
    private int unidadesDisponibles;
    private int unidadesVendidas;
}

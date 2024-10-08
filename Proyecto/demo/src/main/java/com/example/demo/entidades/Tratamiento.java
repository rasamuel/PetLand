package com.example.demo.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Tratamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fecha;
    
    private int cantidad;

    @ManyToOne
    private Medicamento medicamento;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.SET_NULL) 
    @JoinColumn(name = "mascota_id", nullable = true)
    private Pet mascota;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.SET_NULL) 
    @JoinColumn(name = "veterinario_id", nullable = true)
    private Veterinario veterinario;

    public Tratamiento(Long id, Date fecha, int cantidad, Medicamento medicamento, Pet mascota, Veterinario veterinario) {
        this.id = id;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.medicamento = medicamento;
        this.mascota = mascota;
        this.veterinario = veterinario;
    }

    public Tratamiento(){
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public Pet getMascota() {
        return mascota;
    }

    public void setMascota(Pet mascota) {
        this.mascota = mascota;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }
}

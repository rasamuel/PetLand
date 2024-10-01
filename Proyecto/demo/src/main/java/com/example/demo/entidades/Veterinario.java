package com.example.demo.entidades;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Veterinario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String correo;
    private String contrasena;
    private String especialidad;
    private String foto;
    private String nombre;

    public Veterinario(String nombre, String correo,String contrasena, String especialidad, String foto) {
        this.contrasena = contrasena;
        this.correo = contraseña;
        this.especialidad = especialidad;
        this.foto = foto;
        this.nombre = nombre;
    }
    public Veterinario() {
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getCorreo(){
        return correo;
    }
    public void setCorreo(){
        this.correo = correo;
    }
    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
package com.example.demo.entidades;

public class Pet {

    private Long id;
    private String nombre;
    private String raza;
    private String imageUrl;

    // Constructor vacío
    public Pet() {}

    // Constructor con parámetros
    public Pet(Long id, String nombre, String raza, String imageUrl) {
        this.id = id;
        this.nombre = nombre;
        this.raza = raza;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
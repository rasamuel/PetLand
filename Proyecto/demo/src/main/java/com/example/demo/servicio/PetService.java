package com.example.demo.servicio;

import java.util.List;

import com.example.demo.entidades.Pet;

import java.util.List;
import java.util.Optional;


public interface PetService {
    List<Pet> getAllPets();
    Pet getPetById(Long id);
    void addPet(Pet pet);
    void updatePet(Pet pet);
    void deletePet(Long id);
    List<Pet> searchPets(String query);
}
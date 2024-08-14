package com.example.demo.repositorio;

import com.example.demo.entidades.Pet;
import java.util.List;
import java.util.Optional;

public interface PetRepository {
    List<Pet> findAll();
    Pet findById(Long id);
    void save(Pet pet);
    void delete(Long id);
}
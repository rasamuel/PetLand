package com.example.demo.repositorio;

import com.example.demo.entidades.Pet;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class PetRepositoryImpl implements PetRepository {
    private final Map<Long, Pet> petDatabase = new HashMap<>();
    private Long currentId = 1L;

    @Override
    public List<Pet> findAll() {
        return new ArrayList<>(petDatabase.values());
    }

    @Override
    public Pet findById(Long id) {
        return petDatabase.get(id);
    }

    @Override
    public void save(Pet pet) {
        if (pet.getId() == null) {
            pet.setId(currentId++);
        }
        petDatabase.put(pet.getId(), pet);
    }

    @Override
    public void delete(Long id) {
        petDatabase.remove(id);
    }
}
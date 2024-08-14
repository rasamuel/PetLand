package com.example.demo.servicio;

import com.example.demo.entidades.Pet;
import com.example.demo.repositorio.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    public Pet getPetById(Long id) {
        return petRepository.findById(id);
    }

    @Override
    public void addPet(Pet pet) {
        petRepository.save(pet);
    }

    @Override
    public void updatePet(Pet pet) {
        petRepository.save(pet);
    }

    @Override
    public void deletePet(Long id) {
        petRepository.delete(id);
    }

    @Override
    public List<Pet> searchPets(String query) {
        return petRepository.findAll().stream()
                .filter(pet -> pet.getNombre().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}
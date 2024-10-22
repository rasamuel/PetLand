package com.example.demo;


import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.entidades.Owner;
import com.example.demo.entidades.Pet;
import com.example.demo.repositorio.OwnerRepository;
import com.example.demo.repositorio.PetRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest // Para pruebas de repositorio
public class PetRepositoryTests {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    private Pet pet;

    @BeforeEach
    public void setUp() {
        Owner owner = new Owner();
        owner.setId(1L); 
        owner.setNombre("John Doe"); 
        ownerRepository.save(owner); 

        pet = new Pet();
        pet.setNombre("Fido");
        pet.setRaza("Labrador");
        pet.setEnfermedad("Ninguna");
        pet.setEstado(true);
        pet.setEdad(5);
        pet.setPeso(30.5);
        pet.setOwner(owner); 
        petRepository.save(pet);
    }

    @AfterEach
    public void tearDown() {
        petRepository.deleteAll(); // Limpia después de cada prueba
    }

    @Test
    public void testCreatePet() {
        Pet newPet = new Pet();
        newPet.setNombre("Bella");
        newPet.setRaza("Bulldog");
        newPet.setEnfermedad("Ninguna");
        newPet.setEstado(true);
        newPet.setEdad(3);
        newPet.setPeso(25.0);
        newPet.setOwner(pet.getOwner()); // Asocia el mismo dueño

        Pet savedPet = petRepository.save(newPet);
        assertThat(savedPet.getId()).isNotNull();
        assertThat(savedPet.getNombre()).isEqualTo("Bella");
    }

    @Test
    public void testReadPet() {
        Pet foundPet = petRepository.findById(pet.getId()).orElse(null);
        assertThat(foundPet).isNotNull();
        assertThat(foundPet.getNombre()).isEqualTo(pet.getNombre());
    }

    @Test
    public void testUpdatePet() {
        pet.setNombre("Max");
        pet.setRaza("Golden Retriever");
        Pet updatedPet = petRepository.save(pet);
        
        assertThat(updatedPet.getNombre()).isEqualTo("Max");
        assertThat(updatedPet.getRaza()).isEqualTo("Golden Retriever");
    }

    @Test
    public void testDeletePet() {
        petRepository.delete(pet);
        assertThat(petRepository.findById(pet.getId())).isNotPresent();
    }
}
package com.example.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.controlador.PetController;
import com.example.demo.entidades.Owner;
import com.example.demo.entidades.Pet;
import com.example.demo.servicio.OwnerService;
import com.example.demo.servicio.PetService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PetControllerTests {

    @Mock
    private PetService petService;

    @Mock
    private OwnerService ownerService;

    @InjectMocks
    private PetController petController;

    private Pet pet;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        pet = new Pet();
        pet.setId(1L);
        pet.setNombre("Fido");
        pet.setRaza("Labrador");
        pet.setEnfermedad("Ninguna");
        pet.setEstado(true);
        pet.setEdad(5);
        pet.setPeso(30.5);
        
        Owner owner = new Owner();
        owner.setId(1L);
        owner.setNombre("John Doe");
        pet.setOwner(owner);
    }

    @Test
    public void testListPets() {
        List<Pet> pets = new ArrayList<>();
        pets.add(pet);

        when(petService.getAllPets()).thenReturn(pets);

        List<Pet> result = petController.listPets();
        
        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getNombre()).isEqualTo("Fido");
    }

    @Test
    public void testViewPet() {
        when(petService.getPetById(anyLong())).thenReturn(Optional.of(pet));

        Optional<Pet> result = petController.viewPet(1L);
        
        assertThat(result).isPresent();
        assertThat(result.get().getNombre()).isEqualTo("Fido");
    }

    @Test
    public void testAddPet() {
        when(ownerService.getOwnerById(anyLong())).thenReturn(Optional.of(pet.getOwner()));
        when(petService.savePet(any(Pet.class))).thenReturn(pet);

        Pet newPet = petController.addPet(pet, 1L);

        assertThat(newPet).isNotNull();
        assertThat(newPet.getNombre()).isEqualTo("Fido");
    }

    @Test
    public void testActivatePet() {
        when(petService.activatePet(anyLong())).thenReturn(true);
        
        petController.activatePet(1L);
        
        verify(petService, times(1)).activatePet(1L);
    }

    @Test
    public void testDeactivatePet() {
        when(petService.deactivatePet(anyLong())).thenReturn(true);
        
        petController.deactivatePet(1L);
        
        verify(petService, times(1)).deactivatePet(1L);
    }

    @Test
    public void testSearchPets() {
        List<Pet> pets = new ArrayList<>();
        pets.add(pet);

        when(petService.searchPets(any(String.class))).thenReturn(pets);

        List<Pet> result = petController.searchPets("Fido");
        
        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getNombre()).isEqualTo("Fido");
    }
}

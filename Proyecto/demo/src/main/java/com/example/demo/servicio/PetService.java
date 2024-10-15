package com.example.demo.servicio;

import com.example.demo.entidades.Pet;

import java.util.List;
import java.util.Optional;

public interface PetService {
    List<Pet> getAllPets();
    Optional<Pet> getPetById(Long id);
    Pet savePet(Pet pet);
    void deletePet(Long id);
    List<Pet> findByNombre(String nombre);
    List<Pet> findByRaza(String raza);
    List<Pet> findByEnfermedad(String enfermedad);
    List<Pet> findByOwnerId(Long ownerId);
    List<Pet> findByNombreContaining(String texto);
    long countByNombre(String nombre);
    void deleteByNombre(String nombre);
    List<Pet> findAllByOrderByNombreAsc();
    List<Pet> searchPets(String query);
    boolean activatePet(Long id);
    boolean deactivatePet(Long id);
    List<Pet> getActivePets();
    List<Pet> getInactivePets();
    Long contarTotalMascotas();
    Long contarMascotasActivas();
}

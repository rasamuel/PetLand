package com.example.demo.servicio;

import com.example.demo.entidades.Pet;
import com.example.demo.repositorio.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    public Optional<Pet> getPetById(Long id) {
        return petRepository.findById(id);
    }

    @Override
    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }

    @Override
    public List<Pet> findByNombre(String nombre) {
        return petRepository.findByNombre(nombre);
    }

    @Override
    public List<Pet> findByRaza(String raza) {
        return petRepository.findByRaza(raza);
    }

    @Override
    public List<Pet> findByEnfermedad(String enfermedad) {
        return petRepository.findByEnfermedad(enfermedad);
    }


    @Override
    public List<Pet> findByOwnerId(Long ownerId) {
        return petRepository.findByOwnerId(ownerId);
    }

    @Override
    public List<Pet> findByNombreContaining(String texto) {
        return petRepository.findByNombreContaining(texto);
    }

    @Override
    public long countByNombre(String nombre) {
        return petRepository.countByNombre(nombre);
    }

    @Override
    public void deleteByNombre(String nombre) {
        petRepository.deleteByNombre(nombre);
    }

    @Override
    public List<Pet> findAllByOrderByNombreAsc() {
        return petRepository.findAllByOrderByNombreAsc();
    }
    @Override
    public List<Pet> searchPets(String query) {
        // Puedes agregar una búsqueda personalizada aquí, si es necesario
        return petRepository.findByNombreContaining(query);
    }
    @Override
    public boolean activatePet(Long id) {
        Optional<Pet> optionalPet = petRepository.findById(id);
        if (optionalPet.isPresent()) {
            Pet pet = optionalPet.get();
            pet.setEstado(true);
            petRepository.save(pet);
            return true; // Indica que la mascota se activó correctamente
        }
        return false; // Indica que la mascota no fue encontrada
    }

    // Desactiva una mascota
    @Override
    public boolean deactivatePet(Long id) {
        Optional<Pet> optionalPet = petRepository.findById(id);
        if (optionalPet.isPresent()) {
            Pet pet = optionalPet.get();
            pet.setEstado(false);
            petRepository.save(pet);
            return true; // Indica que la mascota se desactivó correctamente
        }
        return false; // Indica que la mascota no fue encontrada
    }

    // Obtiene todas las mascotas activas
    @Override
    public List<Pet> getActivePets() {
        return petRepository.findByEstado(true);
    }

    // Obtiene todas las mascotas inactivas
    @Override
    public List<Pet> getInactivePets() {
        return petRepository.findByEstado(false);
    }

    @Override
    public Long contarTotalMascotas() {
        return petRepository.count();
    }

    @Override
    public Long contarMascotasActivas() {
        return petRepository.countByEstado(true); // Suponiendo que "true" significa que está activa
    }
}


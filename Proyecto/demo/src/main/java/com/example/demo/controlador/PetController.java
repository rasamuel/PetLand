package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entidades.Owner;
import com.example.demo.entidades.Pet;
import com.example.demo.servicio.OwnerService;
import com.example.demo.servicio.PetService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pets") // Cambio para que sea un controlador REST
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private OwnerService ownerService;

    // Mostrar la lista de mascotas (GET)
    @GetMapping
    public List<Pet> listPets() {
        return petService.getAllPets(); // Devuelve la lista de mascotas en JSON
    }

    // Ver detalles de una mascota específica (GET)
    @GetMapping("/{id}")
    public Optional<Pet> viewPet(@PathVariable Long id) {
        return petService.getPetById(id); // Devuelve los detalles de una mascota en JSON
    }

    // Agregar una nueva mascota (POST)
    @PostMapping("/add")
    public Pet addPet(@RequestBody Pet pet, @RequestParam Long ownerId) {
        Optional<Owner> ownerOptional = ownerService.getOwnerById(ownerId);
        if (ownerOptional.isPresent()) {
            pet.setOwner(ownerOptional.get());
            pet.setEstado(true);
            return petService.savePet(pet); // Guardar y devolver la nueva mascota en JSON
        } else {
            throw new RuntimeException("Owner not found");
        }
    }

    // Activar una mascota (PUT)
    @PutMapping("/activate/{id}")
    public void activatePet(@PathVariable Long id) {
        if (!petService.activatePet(id)) {
            throw new RuntimeException("Pet not found");
        }
    }

    // Desactivar una mascota (PUT)
    @PutMapping("/deactivate/{id}")
    public void deactivatePet(@PathVariable Long id) {
        if (!petService.deactivatePet(id)) {
            throw new RuntimeException("Pet not found");
        }
    }

    // Buscar mascotas (GET)
    @GetMapping("/search")
    public List<Pet> searchPets(@RequestParam("query") String query) {
        return petService.searchPets(query); // Devuelve la lista de mascotas que coinciden en JSON
    }

    // Mostrar el formulario para editar una mascota (GET)
    @GetMapping("/{id}/edit")
    public Optional<Pet> editPetForm(@PathVariable("id") Long id) {
        return petService.getPetById(id); // Devuelve la mascota en JSON para ser editada
    }

    // Actualizar una mascota (PUT)
    @PutMapping("/{id}/update")
    public Pet updatePet(@PathVariable("id") Long id, @RequestBody Pet pet) {
        Optional<Pet> optionalPet = petService.getPetById(id);
        if (optionalPet.isPresent()) {
            Pet existingPet = optionalPet.get();
            existingPet.setNombre(pet.getNombre());
            existingPet.setRaza(pet.getRaza());
            existingPet.setImageUrl(pet.getImageUrl());
            existingPet.setEdad(pet.getEdad());
            existingPet.setPeso(pet.getPeso());
            existingPet.setEnfermedad(pet.getEnfermedad());
            existingPet.setEstado(pet.getEstado());

            return petService.savePet(existingPet); // Devuelve la mascota actualizada en JSON
        } else {
            throw new RuntimeException("Pet not found");
        }
    }

    // Mostrar las mascotas activas (GET)
    @GetMapping("/activas")
    public List<Pet> viewActivePets() {
        return petService.getActivePets(); // Devuelve la lista de mascotas activas en JSON
    }

    // Mostrar las mascotas inactivas (GET)
    @GetMapping("/inactivas")
    public List<Pet> viewInactivePets() {
        return petService.getInactivePets(); // Devuelve la lista de mascotas inactivas en JSON
    }

    // Eliminar una mascota (DELETE)
    @DeleteMapping("/delete/{id}")
    public void deletePet(@PathVariable Long id) {
        petService.deletePet(id); // Elimina la mascota
    }
}

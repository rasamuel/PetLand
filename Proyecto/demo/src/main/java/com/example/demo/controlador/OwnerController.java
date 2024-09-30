package com.example.demo.controlador;

import com.example.demo.entidades.Owner;
import com.example.demo.entidades.Pet;
import com.example.demo.servicio.OwnerService;
import com.example.demo.servicio.PetService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/owners") // Cambiado para que sea un API Rest
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private PetService petService;

    // Mostrar la lista de propietarios (GET)
    @GetMapping
    public List<Owner> listOwners() {
        return ownerService.getAllOwners(); // Devuelve la lista de propietarios en formato JSON
    }

    // Ver detalles de un propietario específico (GET)
    @GetMapping("/{id}")
    public Optional<Owner> viewOwner(@PathVariable Long id) {
        return ownerService.getOwnerById(id); // Devuelve el propietario en formato JSON
    }

    // Agregar un nuevo propietario (POST)
    @PostMapping("/add")
    public Owner addOwner(@RequestBody Owner owner) {
        return ownerService.saveOwner(owner); // Guardar el propietario y devolver el objeto creado
    }

    // Eliminar un propietario (DELETE)
    @DeleteMapping("/delete/{id}")
    public void deleteOwner(@PathVariable Long id) {
        ownerService.deleteOwner(id); // Elimina el propietario
    }

    // Actualizar un propietario (PUT)
    @PutMapping("/{id}/update")
    public Owner updateOwner(@PathVariable("id") Long id, @RequestBody Owner owner) {
        Optional<Owner> optionalOwner = ownerService.getOwnerById(id);
        if (optionalOwner.isPresent()) {
            Owner existingOwner = optionalOwner.get();
            existingOwner.setNombre(owner.getNombre());
            existingOwner.setCedula(owner.getCedula());
            existingOwner.setCorreo(owner.getCorreo());
            existingOwner.setCelular(owner.getCelular());
            return ownerService.saveOwner(existingOwner); // Devuelve el propietario actualizado
        } else {
            throw new RuntimeException("Owner not found");
        }
    }

    // Mostrar las mascotas de un propietario logueado (GET)
    @GetMapping("/pets")
    public List<Pet> showOwnerPets(HttpSession session) {
        Long ownerId = (Long) session.getAttribute("loggedInOwnerId");
        if (ownerId == null) {
            throw new RuntimeException("User not logged in");
        }

        return petService.findByOwnerId(ownerId); // Devuelve la lista de mascotas del propietario en formato JSON
    }
}

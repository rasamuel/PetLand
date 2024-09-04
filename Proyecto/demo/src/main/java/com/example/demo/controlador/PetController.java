package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entidades.Owner;
import com.example.demo.entidades.Pet;
import com.example.demo.servicio.OwnerService;
import com.example.demo.servicio.PetService;
import java.util.Optional;


@Controller
@RequestMapping("/pets")
public class PetController {
    @Autowired
    private PetService petService;
    @Autowired
    private OwnerService ownerService;

    // Mostrar la lista de mascotas
    @GetMapping
    public String listPets(Model model) {
        model.addAttribute("pets", petService.getAllPets());
        return "petList";
    }

    // Ver detalles de una mascota específica
    @GetMapping("/{id}")
    public String viewPet(@PathVariable Long id, Model model) {
        Optional<Pet> pet = petService.getPetById(id);
        if (pet.isEmpty()) {
            model.addAttribute("message", "Mascota no encontrada");
            return "error"; // O redirige a una página de error si la mascota no se encuentra
        }
        model.addAttribute("pet", pet.get());
        return "petDetail";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
      model.addAttribute("pet", new Pet());
      model.addAttribute("owners", ownerService.getAllOwners());
      return "petAdd";
    }

    // Agregar una nueva mascota
    @PostMapping("/add")
    public String addPet(@ModelAttribute Pet pet, @RequestParam Long ownerId) {
        Optional<Owner> ownerOptional = ownerService.getOwnerById(ownerId);
        if (ownerOptional.isPresent()) {
            pet.setOwner(ownerOptional.get()); // Asociar la mascota con el dueño
            pet.setEstado(true);
            petService.savePet(pet); // Guardar la mascota
        } else {
            return "redirect:/pets/add?error=ownerNotFound";
        }
        return "redirect:/pets";
    }

    @PostMapping("/activate/{id}")
    public String activatePet(@PathVariable Long id, Model model) {
        boolean success = petService.activatePet(id);
        if (success) {
            model.addAttribute("message", "Mascota activada correctamente.");
        } else {
            model.addAttribute("error", "Mascota no encontrada.");
        }
        return "redirect:/pets/activas";
    }

    @PostMapping("/deactivate/{id}")
    public String deactivatePet(@PathVariable Long id, Model model) {
        boolean success = petService.deactivatePet(id);
        if (success) {
            model.addAttribute("message", "Mascota desactivada correctamente.");
        } else {
            model.addAttribute("error", "Mascota no encontrada.");
        }
        return "redirect:/pets/inactivas";
    }

    // Buscar mascotas
    @GetMapping("/search")
    public String searchPets(@RequestParam("query") String query, Model model) {
        model.addAttribute("pets", petService.searchPets(query));
        return "petList";
    }

    // Mostrar el formulario para editar una mascota
    @GetMapping("/{id}/edit")
    public String editPetForm(@PathVariable("id") Long id, Model model) {
        Optional<Pet> pet = petService.getPetById(id);
        if (pet.isEmpty()) {
            model.addAttribute("message", "Mascota no encontrada");
            return "error"; // O redirige a una página de error si la mascota no se encuentra
        }
        model.addAttribute("pet", pet.get());
        return "petEdit"; // Nombre del archivo HTML para la edición
    }

    // Actualizar una mascota
    @PostMapping("/{id}/update")
    public String updatePet(@PathVariable("id") Long id, @ModelAttribute Pet pet) {
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
    
            petService.savePet(existingPet);
            return "redirect:/pets";
        } else {
            // Manejar el caso en el que la mascota no se encuentra
            return "redirect:/pets";
        }
    }
    
    @GetMapping("/activas")
    public String viewActivePets(Model model) {
        model.addAttribute("pets", petService.getActivePets());
        return "petActive";  // Asegúrate de que petActive.html existe en /templates
    }

    @GetMapping("/inactivas")
    public String viewInactivePets(Model model) {
        model.addAttribute("pets", petService.getInactivePets());
        return "petInactive";  // Asegúrate de que petInactive.html existe en /templates
    }
    @PostMapping("/delete/{id}")
        public String deletePet(@PathVariable Long id) {
            petService.deletePet(id);
            return "redirect:/pets";
        }
    
}

package com.example.demo.controlador;

import com.example.demo.entidades.Owner;
import com.example.demo.entidades.Pet;
import com.example.demo.servicio.OwnerService;
import com.example.demo.servicio.PetService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/owners")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;
    @Autowired
    private PetService petService;



    // Mostrar la lista de propietarios
    @GetMapping
    public String listOwners(Model model) {
        model.addAttribute("owners", ownerService.getAllOwners());
        return "ownerList";
    }

    // Ver detalles de un propietario específico
    @GetMapping("/{id}")
    public String viewOwner(@PathVariable Long id, Model model) {
        Optional<Owner> owner = ownerService.getOwnerById(id);
        if (owner.isEmpty()) {
            model.addAttribute("message", "Propietario no encontrado");
            return "ownerError";
        }
        List<Pet> pets = petService.findByOwnerId(id);
        model.addAttribute("owner", owner.get());
        model.addAttribute("pets", pets);
        return "ownerDetail";
    }

    // Mostrar el formulario para agregar un nuevo propietario
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("owner", new Owner());
        return "ownerAdd";
    }

    // Agregar un nuevo propietario
    @PostMapping("/add")
    public String addOwner(@ModelAttribute Owner owner) {
        ownerService.saveOwner(owner);
        return "redirect:/owners";
    }

    // Eliminar un propietario
    @PostMapping("/delete/{id}")
    public String deleteOwner(@PathVariable Long id) {
        ownerService.deleteOwner(id);
        return "redirect:/owners";
    }

    // Mostrar el formulario para editar un propietario
    @GetMapping("/{id}/edit")
    public String editOwnerForm(@PathVariable("id") Long id, Model model) {
        Optional<Owner> owner = ownerService.getOwnerById(id);
        if (owner.isEmpty()) {
            model.addAttribute("message", "Propietario no encontrado");
            return "errorOwner";
        }
        model.addAttribute("owner", owner.get());
        return "ownerEdit";
    }

    // Actualizar un propietario
    @PostMapping("/{id}/update")
    public String updateOwner(@PathVariable("id") Long id, @ModelAttribute Owner owner) {
        System.out.println("ID: " + id);
        System.out.println("Owner before update: " + owner);
    
        // Obtiene el Owner existente usando Optional
        Optional<Owner> optionalOwner = ownerService.getOwnerById(id);
        if (optionalOwner.isPresent()) {
            Owner existingOwner = optionalOwner.get();
            existingOwner.setNombre(owner.getNombre());
            existingOwner.setCedula(owner.getCedula());
            existingOwner.setCorreo(owner.getCorreo());
            existingOwner.setCelular(owner.getCelular());
            ownerService.saveOwner(existingOwner);
        } else {
            // Manejar el caso en el que el owner no se encuentra
            System.out.println("Owner with ID " + id + " not found.");
            // Redirige o maneja el error según lo necesario
            return "redirect:/owners?error=notfound";
        }
    
        return "redirect:/owners";
    }
    


    // Iniciar sesión con cédula

    @GetMapping("/pets")
    public String showOwnerPets(HttpSession session, Model model) {
        Long ownerId = (Long) session.getAttribute("loggedInOwnerId"); // Obtén el ID del propietario de la sesión
    
        if (ownerId == null) {
            return "redirect:/login"; // Redirige al login si no hay un ID en la sesión
        }
    
        Optional<Owner> owner = ownerService.getOwnerById(ownerId);
        if (owner.isEmpty()) {
            model.addAttribute("message", "Propietario no encontrado");
            return "ownerError"; // Muestra un mensaje de error si el propietario no se encuentra
        }
    
        List<Pet> pets = petService.findByOwnerId(ownerId);
        System.out.println("Owner ID: " + ownerId);
        System.out.println("Pets found: " + pets);
        model.addAttribute("pets", pets);
        return "ownerPets"; // Redirige a la vista de mascotas
    }
    
}

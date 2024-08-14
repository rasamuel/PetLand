package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.demo.entidades.Pet;
import com.example.demo.servicio.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pets")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public String listPets(Model model) {
        model.addAttribute("pets", petService.getAllPets());
        return "petList";
    }

    @GetMapping("/{id}")
    public String viewPet(@PathVariable Long id, Model model) {
        model.addAttribute("pet", petService.getPetById(id));
        return "petDetail";
    }

    @PostMapping("/add")
    public String addPet(@ModelAttribute Pet pet) {
        petService.addPet(pet);
        return "redirect:/pets";
    }

    @PostMapping("/delete/{id}")
    public String deletePet(@PathVariable Long id) {
        petService.deletePet(id);
        return "redirect:/pets";
    }

    @GetMapping("/search")
    public String searchPets(@RequestParam String query, Model model) {
        model.addAttribute("pets", petService.searchPets(query));
        return "petList";
    }
}
package com.example.demo.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/vet")
public class VetController {

    @GetMapping("/portal")
    public String mostrarPortalVeterinario(Model model) {
        model.addAttribute("mensaje", "Bienvenido al Portal del Veterinario");
        return "vetPortal"; 
    }
}

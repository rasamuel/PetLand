package com.example.demo.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LandingController {

   // Muestra la página de inicio
   @GetMapping("/")
   public String landing() {
      return "landing.html"; // Asegúrate de tener landing.html en tu carpeta de templates
   }
   
   @GetMapping("/owners/login")
    public String showLoginForm(Model model) {
        model.addAttribute("cedula", "");
        return "ownerLogin";
    }

    @GetMapping("/vet/login")
    public String showVetLoginForm(Model model) {
        return "vetLogin";
    }

}

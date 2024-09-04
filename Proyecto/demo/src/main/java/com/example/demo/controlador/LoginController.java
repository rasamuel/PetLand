package com.example.demo.controlador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.example.demo.entidades.Owner;
import com.example.demo.entidades.Veterinario;
import com.example.demo.servicio.OwnerService;
import com.example.demo.servicio.VeterinarioService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private OwnerService ownerService;
    @Autowired
    private VeterinarioService veterinarioService;


    @PostMapping("/owners/login")
    public String login(@RequestParam("cedula") String cedula, Model model, HttpSession session) {
        Optional<Owner> owner = ownerService.authenticateByCedula(cedula);
        if (owner.isPresent()) {
            session.setAttribute("loggedInOwnerId", owner.get().getId()); // Almacena solo el ID
            return "redirect:/owners/pets"; // Redirige a la página de mascotas
        } else {
            model.addAttribute("error", "Cédula inválida");
            return "ownerLogin"; // Regresa a la página de login si el propietario no es válido
        }
    }
    @PostMapping("/vet/login")
    public String veterinarioLogin(@RequestParam("nombre") String nombre, @RequestParam("contrasena") String contrasena, Model model, HttpSession session) {
        Optional<Veterinario> veterinario = veterinarioService.authenticate(nombre, contrasena);
        if (veterinario.isPresent()) {
            session.setAttribute("loggedInVeterinarioId", veterinario.get().getId()); // Store only the ID
            return "redirect:/vet/portal"; 
        } else {
            model.addAttribute("error", "Cédula o contraseña inválidos");
            return "vetLogin"; 
        }
    }
    @PostMapping("/logout")
    public String logout(SessionStatus status) {
        status.setComplete();
        return "redirect:/login"; // Redirige a la página de inicio de sesión o a la página de inicio
    }
}

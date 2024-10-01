package com.example.demo.controlador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entidades.Owner;
import com.example.demo.entidades.Veterinario;
import com.example.demo.servicio.OwnerService;
import com.example.demo.servicio.VeterinarioService;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class LoginController {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private VeterinarioService veterinarioService;

    // Login de Owner
    @PostMapping("/owners/login")
    public ResponseEntity<?> loginOwner(@RequestParam("cedula") String cedula, HttpSession session) {
        Optional<Owner> owner = ownerService.authenticateByCedula(cedula);
        if (owner.isPresent()) {
            // Guarda el ID del dueño en la sesión
            session.setAttribute("loggedInOwnerId", owner.get().getId());
            return ResponseEntity.ok(owner.get().getId());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Cédula inválida");
        }
    }
    

    // Login de Veterinario
    @PostMapping("/vet/login")
    public ResponseEntity<?> loginVeterinario(@RequestParam("correo") String correo, @RequestParam("contrasena") String contrasena) {
        Optional<Veterinario> veterinario = veterinarioService.authenticate(correo, contrasena);
        if (veterinario.isPresent()) {
            // Si se autentica, devuelve el ID o un token JWT
            return ResponseEntity.ok(veterinario.get().getId()); // Devuelve el ID del veterinario autenticado
        } else {
            // Si no se autentica, devuelve un mensaje de error
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Correo o contraseña inválidos");
        }
    }
    

    // Logout
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();  // Invalida la sesión
        return ResponseEntity.ok("Logout exitoso");
    }
}

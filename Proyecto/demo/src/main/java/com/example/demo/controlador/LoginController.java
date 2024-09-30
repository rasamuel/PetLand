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

@RestController
@CrossOrigin(origins = "*") // Permitir que cualquier dominio consuma este servicio
public class LoginController {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private VeterinarioService veterinarioService;

    // Login de Owner
    @PostMapping("/owners/login")
    public ResponseEntity<?> loginOwner(@RequestParam("cedula") String cedula) {
        Optional<Owner> owner = ownerService.authenticateByCedula(cedula);
        if (owner.isPresent()) {
            // Si se autentica, devuelve el ID o un token JWT
            return ResponseEntity.ok(owner.get().getId()); // Devuelve el ID del propietario autenticado
        } else {
            // Si no se autentica, devuelve un mensaje de error
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Cédula inválida");
        }
    }

    // Login de Veterinario
    @PostMapping("/vet/login")
    public ResponseEntity<?> loginVeterinario(@RequestParam("nombre") String nombre, @RequestParam("contrasena") String contrasena) {
        Optional<Veterinario> veterinario = veterinarioService.authenticate(nombre, contrasena);
        if (veterinario.isPresent()) {
            // Si se autentica, devuelve el ID o un token JWT
            return ResponseEntity.ok(veterinario.get().getId()); // Devuelve el ID del veterinario autenticado
        } else {
            // Si no se autentica, devuelve un mensaje de error
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nombre o contraseña inválidos");
        }
    }

    // Logout (opcional)
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        // Como no estás manejando sesiones de servidor, simplemente devolvemos un OK.
        return ResponseEntity.ok("Logout exitoso");
    }
}

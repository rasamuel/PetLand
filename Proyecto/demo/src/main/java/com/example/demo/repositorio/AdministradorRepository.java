package com.example.demo.repositorio;

import com.example.demo.entidades.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

    Optional<Administrador> findByCorreo(String correo);

    List<Administrador> findByNombre(String nombre);

    List<Administrador> findByNombreContaining(String keyword);

    Optional<Administrador> findByNombreAndCorreo(String nombre, String correo);

    List<Administrador> findByTelefonoStartingWith(String prefix);

    List<Administrador> findByNombreIgnoreCase(String nombre);
    
    List<Administrador> findByContrasena(String contrasena);

    List<Administrador> findByNombreContainingOrCorreoContaining(String nombreKeyword, String correoKeyword);
    
    Optional<Administrador> findByCorreoAndContrasena(String correo, String contrasena);
}

package com.example.demo.repositorio;

import com.example.demo.entidades.Owner;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    Owner findByCorreo(String correo);
    List<Owner> findByNombre(String nombre);
    Optional<Owner> findByCedula(String cedula);
    Optional<Owner> findByCelular(String celular);
    List<Owner> findByNombreContaining(String texto);
    List<Owner> findByPetsNombre(String nombre);
    List<Owner> findByPetsRaza(String raza);
    @Query("SELECT o FROM Owner o WHERE SIZE(o.pets) > :number")
    List<Owner> findOwnersWithMoreThanNumberOfPets(@Param("number") int number);
    long countByCedula(String cedula);
    void deleteByCedula(String cedula);
    List<Owner> findAllByOrderByNombreAsc();
    List<Owner> findByNombreContainingIgnoreCaseOrCorreoContainingIgnoreCase(String nombre, String correo);
}
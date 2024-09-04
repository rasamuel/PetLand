package com.example.demo.servicio;

import com.example.demo.entidades.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerService {
    List<Owner> getAllOwners();
    Optional<Owner> getOwnerById(Long id);
    Owner saveOwner(Owner owner);
    void deleteOwner(Long id);
    List<Owner> findByNombre(String nombre);
    Optional<Owner> findByCedula(String cedula);
    Optional<Owner> findByCelular(String celular);
    List<Owner> findByNombreContaining(String texto);
    List<Owner> findByPetsNombre(String nombre);
    List<Owner> findByPetsRaza(String raza);
    List<Owner> findOwnersWithMoreThanNumberOfPets(int number);
    long countByCedula(String cedula);
    void deleteByCedula(String cedula);
    List<Owner> findAllByOrderByNombreAsc();
    Optional<Owner> authenticateByCedula(String cedula);
}

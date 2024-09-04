package com.example.demo.repositorio;

import com.example.demo.entidades.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PetRepository extends JpaRepository<Pet, Long> {

    // Buscar por nombre
    List<Pet> findByNombre(String nombre);

    // Buscar por raza
    List<Pet> findByRaza(String raza);

    // Buscar por enfermedad
    List<Pet> findByEnfermedad(String enfermedad);

    // Buscar por estado
    List<Pet> findByEstado(boolean estado);

    // Buscar por propietario
    List<Pet> findByOwnerId(Long ownerId);

    // Buscar mascotas cuyo nombre contenga un texto
    List<Pet> findByNombreContaining(String texto);

    // Buscar mascotas por nombre de propietario (usando una consulta personalizada)
    @Query("SELECT p FROM Pet p WHERE p.owner.nombre = :nombre")
    List<Pet> findByOwnerNombre(@Param("nombre") String nombre);

    // Buscar mascotas por cedula de propietario (usando una consulta personalizada)
    @Query("SELECT p FROM Pet p WHERE p.owner.cedula = :cedula")
    List<Pet> findByOwnerCedula(@Param("cedula") String cedula);

    // Contar mascotas por nombre
    long countByNombre(String nombre);

    // Eliminar mascotas por nombre
    void deleteByNombre(String nombre);

    // Obtener todas las mascotas ordenadas por nombre ascendente
    List<Pet> findAllByOrderByNombreAsc();

    List<Pet> findByRazaContaining(String raza);
}
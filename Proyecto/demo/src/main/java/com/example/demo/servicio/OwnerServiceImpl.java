package com.example.demo.servicio;

import com.example.demo.entidades.Owner;
import com.example.demo.entidades.Role;
import com.example.demo.entidades.UserEntity;
import com.example.demo.repositorio.OwnerRepository;
import com.example.demo.repositorio.RoleRepository;
import com.example.demo.repositorio.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public OwnerServiceImpl(OwnerRepository ownerRepository, UserRepository userRepository, RoleRepository roleRepository) {
        this.ownerRepository = ownerRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    @Override
    public Optional<Owner> getOwnerById(Long id) {
        return ownerRepository.findById(id);
    }

    @Override
    public Owner saveOwner(Owner owner) {
        // Guardar el Owner en la base de datos
        Owner savedOwner = ownerRepository.save(owner);
        
        // Asignar el rol "CLIENTE" y crear el UserEntity asociado
        Role clienteRole = roleRepository.findByName("CLIENTE")
            .orElseThrow(() -> new RuntimeException("Role CLIENTE not found"));
        
        createUserForOwner(savedOwner, clienteRole);

        return savedOwner;
    }

    @Override
    public void deleteOwner(Long id) {
        ownerRepository.deleteById(id);
    }

    @Override
    public List<Owner> findByNombre(String nombre) {
        return ownerRepository.findByNombre(nombre);
    }

    @Override
    public Optional<Owner> findByCedula(String cedula) {
        return ownerRepository.findByCedula(cedula);
    }

    @Override
    public Optional<Owner> findByCelular(String celular) {
        return ownerRepository.findByCelular(celular);
    }

    @Override
    public List<Owner> findByNombreContaining(String texto) {
        return ownerRepository.findByNombreContaining(texto);
    }

    @Override
    public List<Owner> findByPetsNombre(String nombre) {
        return ownerRepository.findByPetsNombre(nombre);
    }

    @Override
    public List<Owner> findByPetsRaza(String raza) {
        return ownerRepository.findByPetsRaza(raza);
    }

    @Override
    public List<Owner> findOwnersWithMoreThanNumberOfPets(int number) {
        return ownerRepository.findOwnersWithMoreThanNumberOfPets(number);
    }

    @Override
    public long countByCedula(String cedula) {
        return ownerRepository.countByCedula(cedula);
    }

    @Override
    public void deleteByCedula(String cedula) {
        ownerRepository.deleteByCedula(cedula);
    }

    @Override
    public List<Owner> findAllByOrderByNombreAsc() {
        return ownerRepository.findAllByOrderByNombreAsc();
    }

    @Override
    public Optional<Owner> authenticateByCedula(String cedula) {
        return ownerRepository.findByCedula(cedula);
    }

    @Override
    public List<Owner> searchOwners(String query) {
        return ownerRepository.findByNombreContainingIgnoreCaseOrCorreoContainingIgnoreCase(query, query);
    }

    // Método privado para crear un UserEntity con rol CLIENTE
    private void createUserForOwner(Owner owner, Role clienteRole) {
        UserEntity userClient = new UserEntity();
        userClient.setUsername(owner.getCedula()); // Usa la cédula como nombre de usuario
        userClient.setPassword("defaultPassword"); // Usa una contraseña predeterminada
        userClient.setEmail(owner.getCorreo());
        userClient.getRoles().add(clienteRole); // Asigna el rol "CLIENTE"
        
        // Guardar el UserEntity en la base de datos
        userRepository.save(userClient);
    }
}

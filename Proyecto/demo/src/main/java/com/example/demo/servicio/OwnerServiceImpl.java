package com.example.demo.servicio;

import com.example.demo.entidades.Owner;
import com.example.demo.repositorio.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

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
        return ownerRepository.save(owner);
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
}

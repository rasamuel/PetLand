package com.example.demo.servicio;

import com.example.demo.entidades.Veterinario;
import com.example.demo.entidades.Role;
import com.example.demo.entidades.UserEntity;
import com.example.demo.repositorio.VeterinarioRepository;
import com.example.demo.repositorio.RoleRepository;
import com.example.demo.repositorio.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeterinarioServiceImpl implements VeterinarioService {

    private final VeterinarioRepository veterinarioRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public VeterinarioServiceImpl(VeterinarioRepository veterinarioRepository, UserRepository userRepository, RoleRepository roleRepository) {
        this.veterinarioRepository = veterinarioRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<Veterinario> authenticate(String correo, String contrasena) {
        return veterinarioRepository.findByCorreoAndContrasena(correo, contrasena);
    }

    @Override
    public List<Veterinario> getAllVeterinarios() {
        return veterinarioRepository.findAll();
    }

    @Override
    public Optional<Veterinario> getVeterinarioById(Long id) {
        return veterinarioRepository.findById(id);
    }

    @Override
    public Veterinario createVeterinario(Veterinario veterinario) {
        // Guardar el Veterinario en la base de datos
        Veterinario savedVeterinario = veterinarioRepository.save(veterinario);
        
        // Asignar el rol "VETERINARIO" y crear el UserEntity asociado
        Role veterinarioRole = roleRepository.findByName("VETERINARIO")
            .orElseThrow(() -> new RuntimeException("Role VETERINARIO not found"));
        
        createUserForVeterinario(savedVeterinario, veterinarioRole);

        return savedVeterinario;
    }

    @Override
    public Optional<Veterinario> updateVeterinario(Long id, Veterinario veterinarioDetails) {
        return veterinarioRepository.findById(id).map(veterinario -> {
            veterinario.setNombre(veterinarioDetails.getNombre());
            veterinario.setCorreo(veterinarioDetails.getCorreo());
            veterinario.setContrasena(veterinarioDetails.getContrasena());
            veterinario.setEspecialidad(veterinarioDetails.getEspecialidad());
            veterinario.setFoto(veterinarioDetails.getFoto());
            veterinario.setEstado(veterinarioDetails.isEstado());
            return veterinarioRepository.save(veterinario);
        });
    }

    @Override
    public boolean deleteVeterinario(Long id) {
        if (veterinarioRepository.existsById(id)) {
            veterinarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Veterinario> searchVeterinarios(String query) {
        return veterinarioRepository.findByNombreContainingOrEspecialidadContaining(query, query);
    }

    @Override
    public Optional<Veterinario> findById(Long id) {
        return veterinarioRepository.findById(id);
    }

    @Override
    public Long contarVeterinariosActivos() {
        return veterinarioRepository.countByEstado(true);
    }

    @Override
    public Long contarVeterinariosInactivos() {
        return veterinarioRepository.countByEstado(false);
    }

    // Método privado para crear un UserEntity con rol VETERINARIO
    private void createUserForVeterinario(Veterinario veterinario, Role veterinarioRole) {
        UserEntity userVet = new UserEntity();
        userVet.setUsername(veterinario.getCorreo()); // Usa el correo como nombre de usuario
        userVet.setPassword("defaultPassword"); // Usa una contraseña predeterminada
        userVet.setEmail(veterinario.getCorreo());
        userVet.getRoles().add(veterinarioRole); // Asigna el rol "VETERINARIO"
        
        // Guardar el UserEntity en la base de datos
        userRepository.save(userVet);
    }
}

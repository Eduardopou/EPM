package com.example.PruebaSesion.service;

import com.example.PruebaSesion.ERole;
import com.example.PruebaSesion.controllers.request.CreateUserDTO;
import com.example.PruebaSesion.exceptions.UsuarioYaExisteException;
import com.example.PruebaSesion.models.RoleEntity;
import com.example.PruebaSesion.models.UserEntity;
import com.example.PruebaSesion.repositories.RoleRepository;
import com.example.PruebaSesion.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServicioImpl implements UsuarioServicio, UserDetailsService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private  RoleRepository roleRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Override
    public UserEntity buscarPorEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public UserEntity guardar(CreateUserDTO dto) {
        //  1. Verificar existencia por email
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new UsuarioYaExisteException("El usuario con ese email ya existe");
        }

        //  2. Buscar roles existentes en la BD
        Set<RoleEntity> roles = dto.getRoles().stream()
                .map(rol -> roleRepository.findByName(ERole.valueOf(rol))
                        .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + rol)))
                .collect(Collectors.toSet());

        //  3. Crear usuario con roles válidos
        UserEntity user = UserEntity.builder()
                .email(dto.getEmail())
                .nombre(dto.getNombre())
                .password(passwordEncoder.encode(dto.getPassword()))
                .roles(roles)
                .build();

        return userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        return new User(

                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(rol -> new SimpleGrantedAuthority(rol.getName().name()))
                        .collect(Collectors.toSet())
        );
    }

    @Override
    public List<UserEntity> listarUsuarios() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity actualizarUsuario(String currentUsername, UserEntity usuarioActualizado) {
        return null;
    }

    @Override
    public UserEntity actualizarUsuario(String currentUsername, CreateUserDTO usuarioActualizado) {
        // 1. Buscar el usuario actual en la base de datos
        UserEntity usuarioExistente = userRepository.findByEmail(currentUsername);
        if (usuarioExistente == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + currentUsername);
        }

        // 2. Validar que el nuevo email no esté siendo usado por otro usuario (si cambió)
        if (!usuarioExistente.getEmail().equals(usuarioActualizado.getEmail())) {
            if (userRepository.existsByEmail(usuarioActualizado.getEmail())) {
                throw new UsuarioYaExisteException("El nuevo email ya está en uso por otro usuario");
            }
        }

        // 3. Actualizar solo los campos permitidos (no permitir cambiar roles o contraseña aquí)
        usuarioExistente.setEmail(usuarioActualizado.getEmail());
        usuarioExistente.setNombre(usuarioActualizado.getNombre());

        // 4. Actualizar otros campos personalizados si existen
        if (usuarioActualizado.getNombre() != null) {
            usuarioExistente.setNombre(usuarioActualizado.getNombre());
        }
        // 5. Guardar el usuario actualizado
        return userRepository.save(usuarioExistente);
    }

}

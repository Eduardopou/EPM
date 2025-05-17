package com.example.PruebaSesion.service;

import java.util.List;

import com.example.PruebaSesion.controllers.request.CreateUserDTO;
import com.example.PruebaSesion.models.UserEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;




public interface UsuarioServicio extends UserDetailsService{

    UserEntity buscarPorEmail(String email);

    public UserEntity guardar(CreateUserDTO registroDTO);

    public List<UserEntity> listarUsuarios();

    public UserEntity actualizarUsuario(String currentUsername, UserEntity usuarioActualizado);

    public UserEntity actualizarUsuario(String currentUsername, CreateUserDTO usuarioActualizado);
}
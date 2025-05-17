package com.example.PruebaSesion.security;

import com.example.PruebaSesion.models.UserEntity;
import com.example.PruebaSesion.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository usuarioRepository;//Inyecta el repositorio de usuarios, que nos permite consultar la base de datos para buscar usuarios por su email.

    //Este método:
//Busca al usuario en la base de datos usando su email (que en este caso se usa como nombre de usuario).
// Si lo encuentra, envuelve ese usuario en una instancia de CustomUserDetails, que implementa UserDetails (Spring Security usa esta interfaz para autenticar).
//Si no lo encuentra, debería lanzar una excepción UsernameNotFoundException
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity usuario = usuarioRepository.findByEmail(email);
        return new CustomUserDetails(usuario);
    }

}

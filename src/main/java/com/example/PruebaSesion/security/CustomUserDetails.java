package com.example.PruebaSesion.security;

import com.example.PruebaSesion.models.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    // SpringSecurity ya trabaja  con una clase por defecto para poder manejar los datos del usuario autenticado
    //Por ejemplo, si quisieramos el correo, nombre etc, tenemos la clase UserDetails, el problema de esa clase es que queda
    //limitada y tiene un numero limitado de atributos, si es necesario revisarlos puedes checarla ya que esta clase usa
    //implements de ella.
    //El objetivo de esta clase es poder tener mas libertad a la hora de obtener los datos del usuario verificado, ademas de que podemos nombrar a
    //los metodos como nosotros queramos, por ejemplo, en la parte de abajo, por defecto al llamar a getName, se obtiene el
    //correo electronico, pero yo lo personalice para que traiga el username, ya que en la vista solo me interesa tener el
    //nombre de usuario y no el correo.

    private final UserEntity usuario;

    public CustomUserDetails(UserEntity usuario) {
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(usuario.getRoles().toString()));
    }

    @Override
    public String getPassword() {
        return usuario.getPassword();
    }

    @Override
    public String getUsername() {
        return usuario.getEmail(); // <- se usa como username
    }


  public String getNombre() {
        return usuario.getNombre();
  }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

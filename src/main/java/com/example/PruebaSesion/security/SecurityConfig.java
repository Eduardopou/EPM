package com.example.PruebaSesion.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityConfig {


//¿Qué hace este método?
//Este método define un DaoAuthenticationProvider como bean, que Spring Security usará para autenticar a los usuarios.
// DaoAuthenticationProvider: Es un proveedor de autenticación que utiliza un UserDetailsService para cargar los datos del usuario (como usuario, contraseña, roles...) y un PasswordEncoder para verificar la contraseña.
//setUserDetailsService(...): Le indica al proveedor que use la implementación personalizada de UserDetailsService (en este caso, CustomUserDetailsService).
//setPasswordEncoder(...): Le dice cómo comparar contraseñas. Esto es esencial porque las contraseñas están codificadas (por ejemplo, con BCrypt) y Spring necesita el encoder correcto para hacer el match.
    @Bean
    public DaoAuthenticationProvider authenticationProvider(@Qualifier("customUserDetailsService") UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        auth.setPasswordEncoder(passwordEncoder);
        return auth;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)

                //Indicamos cuales seran los endpoints que no van a necesitar autenticacion
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/registro","/", "/js/**", "/css/**", "/images/**",  "/api/gemini/generar").permitAll()
                        .anyRequest().authenticated()//cualquier otro endpoint va a necesitar verificacion
                )
                //Indicamos cual sera la pagina login, si este punto no se indica, Spring da un login por defecto
                .formLogin(form -> form.loginPage("/login")
                        .defaultSuccessUrl("/").permitAll())//Indicamos a que endpoint se va a dirigir despues de la autenticacion

                //Indicamos las caracteristicas del logout
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .invalidateHttpSession(true)//Una vez que se cierre la sesion, eliminarla
                        .clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .sessionManagement(session -> {
                    // Previene ataques de fijación de sesión (cambia el ID de sesión al autenticar)
                    session.sessionFixation(SessionManagementConfigurer.SessionFixationConfigurer::migrateSession
                    );

                })
                .build();
    }


    //Objeto para encriptar las passwords
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}

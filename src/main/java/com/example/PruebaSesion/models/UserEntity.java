package com.example.PruebaSesion.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Set;

@Builder
@Entity
@Data //Con data generamos los setters and getters
@AllArgsConstructor //Generamos el constructor con todos los parametros
@NoArgsConstructor //Generamos el constructor sin parametros
@Table(name = "users")
public class UserEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false) //Con esta etiqueta aseguramos que es unico y que no puede ser nulo

    private Long id;

    @Email
    @NotBlank
    @Size(min = 3, max = 50)
    private String email;

    @NotBlank
    @Size(max = 30)
    private String nombre;

    @NotBlank
    private String password;

    //Set no permite elementos repetidos
    //Eager trae todos los roles asociados al usuario de una sola vez, targetEntity es la entidad con la que se va a establecer la relacion
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = RoleEntity.class, cascade = CascadeType.PERSIST)    //Como es una relacion de muchos a muchos, se crea una tabla intermedia con las llaves foraneas correspondientes, Persist evita que se borre tambien la relacion de la llave foranea
    @JoinTable(name = "user_roles",  //nombre de la tabla para la relacion
    joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id")) //nombre de las llaves foraneas
    private Set<RoleEntity> roles;

}

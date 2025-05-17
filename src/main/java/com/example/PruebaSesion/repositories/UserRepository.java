package com.example.PruebaSesion.repositories;

import com.example.PruebaSesion.models.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByNombre(String username);
    UserEntity findByEmail(String username);

    //Ejemplo de una query personalizada

    @Query("select u " +    //Selecciona algo
            "from UserEntity u " +  //De la entidad
            "where u.nombre = ?1") //Que cumpla con la entrada de la funcion (username)
    Optional<UserEntity> getName(String username);

    boolean existsByEmail(@Email(message = "Email must be valid") @NotBlank(message = "Email is required") String email);
}

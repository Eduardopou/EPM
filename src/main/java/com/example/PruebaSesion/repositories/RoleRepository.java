package com.example.PruebaSesion.repositories;

import com.example.PruebaSesion.ERole;
import com.example.PruebaSesion.models.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByName(ERole name);

}

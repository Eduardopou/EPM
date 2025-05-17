package com.example.PruebaSesion.repositories;

import com.example.PruebaSesion.models.UsuarioAnimal;
import com.example.PruebaSesion.models.relations.UsuarioAnimalId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioAnimalRepository extends JpaRepository<UsuarioAnimal, UsuarioAnimalId> {



    List<UsuarioAnimal> findByUsuarioId(Long usuarioId);

    boolean existsByIdUsuarioIdAndIdAnimalId(Long usuarioId, Long animalId);

    void deleteByIdUsuarioIdAndIdAnimalId(Long usuarioId, Long animalId);
}

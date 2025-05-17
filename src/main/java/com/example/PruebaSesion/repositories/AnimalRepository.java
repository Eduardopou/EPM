package com.example.PruebaSesion.repositories;


import java.util.ArrayList;
import java.util.Optional;

import com.example.PruebaSesion.models.AnimalModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AnimalRepository extends JpaRepository<AnimalModel, Long> {

    public abstract Optional<AnimalModel> findByIdAnimal(Long idAnimal);
    public abstract Optional<AnimalModel> findByNombreComun(String nombreComun);
    public abstract Optional<AnimalModel> findByNombreCientifico(String nombreCientifico);
    public abstract ArrayList<AnimalModel> findByRiesgo(Integer riesgo);
}




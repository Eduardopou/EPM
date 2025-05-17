package com.example.PruebaSesion.repositories;

import java.util.Optional;

import com.example.PruebaSesion.models.ProgramasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository // Indica que esta interfaz es un componente de acceso a datos (DAO)
public interface ProgramasRepository extends JpaRepository<ProgramasModel, Long> {


    // Buscar un programa por su nombre
    public abstract Optional<ProgramasModel> findByNombre(String nombre);
}


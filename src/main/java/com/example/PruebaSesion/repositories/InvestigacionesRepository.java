package com.example.PruebaSesion.repositories;

import java.util.ArrayList;
import java.util.Optional;

import com.example.PruebaSesion.models.InvestigacionesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository //Indica que es un servicio.
public interface InvestigacionesRepository extends JpaRepository<InvestigacionesModel, Long> {//Esto es una interfaz que extiende de JpaRepository, lo que permite realizar operaciones CRUD en la base de datos.

    public abstract Optional<InvestigacionesModel> findByIdInvestigacion(Long idInvestigacion); //Metodo para buscar una investigacion por su ID.
    public abstract Optional<InvestigacionesModel> findByNombreInvestigador(String nombreInvestigador);
    
}

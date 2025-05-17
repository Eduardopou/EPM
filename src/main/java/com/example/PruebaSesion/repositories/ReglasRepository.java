package com.example.PruebaSesion.repositories;

import java.util.Optional;

import com.example.PruebaSesion.models.ReglasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository //Indica que es un servicio
public interface ReglasRepository extends JpaRepository<ReglasModel, Long> { //Esto es una interfaz que extiende de JpaRepository, lo que permite realizar operaciones CRUD en la base de datos
//JpaRepository es una interfaz de Spring Data JPA que proporciona métodos para realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) en una entidad específica. En este caso, la entidad es ReglasModel y el tipo de dato de la clave primaria es Long.
    
    public abstract Optional<ReglasModel> findByNumeroRegla(int numeroRegla); //Método para buscar una regla por su ID
    public abstract Optional<ReglasModel> findByDescripcionRegla(String descripcionRegla); //Método para buscar una regla por su descripción
}
//Esta interfaz extiende de JpaRepository, lo que significa que hereda todos los métodos CRUD y otros métodos útiles para interactuar con la base de datos. Además, se pueden definir métodos personalizados como findByIdRegla y findByDescripcionRegla para realizar consultas específicas en la tabla de reglas. Estos métodos utilizan la convención de nomenclatura de Spring Data JPA para generar automáticamente las consultas SQL necesarias.
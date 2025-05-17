package com.example.PruebaSesion.service;

import java.util.ArrayList;
import java.util.Optional;

import com.example.PruebaSesion.models.ReglasModel;
import com.example.PruebaSesion.repositories.ReglasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReglasService { //Creamos la clase ReglasService que se encargara de la logica de negocio de las reglas
//Esta clase se encargara de obtener las reglas de la base de datos

    @Autowired //Con esta etiqueta ya no es necesario instanciar la clase ya que SpringBoot sabe que existe
    ReglasRepository reglasRepository; //Creamos el objeto para poder usar los metodos



public ArrayList<ReglasModel> obtenerReglas(){ //Obtiene todas las reglas de la base de datos en un arraylist

    return (ArrayList<ReglasModel>) reglasRepository.findAll(); //Devuelve un arraylist con todas las reglas de la base de datos
    
}

public Optional<ReglasModel> obtenerConNumeroRegla(int numeroRegla){ //Obtiene una regla por su id

    Optional<ReglasModel> verRegla = this.reglasRepository.findByNumeroRegla(numeroRegla); //Busca la regla por su id en la base de datos
    
         if (verRegla.isPresent()) { //Si la regla existe
           
             return verRegla; //Devuelve la regla
         }
         else{ //Si no existe
         throw new RuntimeException("Datos incorrectos"); //Lanza una excepcion
         }    
}

public Optional<ReglasModel> obtenerConDescripcionRegla(String descripcionRegla){ //Obtiene una regla por su descripcion

    Optional<ReglasModel> verRegla = this.reglasRepository.findByDescripcionRegla(descripcionRegla); //Busca la regla por su descripcion en la base de datos
    
         if (verRegla.isPresent()) { //Si la regla existe
           
             return verRegla; //Devuelve la regla
         }
         else{ //Si no existe
         throw new RuntimeException("Datos incorrectos"); //Lanza una excepcion
         }    
}

public Optional<ReglasModel> obtenerPorId (Long idRegla){ //Obtiene una regla por su id

    return reglasRepository.findById(idRegla); //Busca la regla por su id en la base de datos
}
}

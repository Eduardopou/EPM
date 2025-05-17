package com.example.PruebaSesion.service;

import java.util.ArrayList;
import java.util.Optional;

import com.example.PruebaSesion.models.AnimalModel;
import com.example.PruebaSesion.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {
  @Autowired //Con esta etiqueta ya no es necesario instanciar la clase ya que SpringBoot sabe que existe
  AnimalRepository animalRepository; //Creamos el objeto para poder usar los metodos

    public Optional<AnimalModel> findAnimalById(Long id) {

        return animalRepository.findByIdAnimal(id);

    }


 public ArrayList<AnimalModel> obtenerAnimales(){
   
      return (ArrayList<AnimalModel>) animalRepository.findAll(); //Devuelve un arraylist con todos los animales de la base de datos

  
}
 
 public AnimalModel guardarAnimal(AnimalModel animal){
   //Si al metodo save le enviamos un Id, lo toma como una actualizacion, y si no. sabe que es un usuario nuevo
   
Optional<AnimalModel> verAnimal = animalRepository.findByNombreCientifico(animal.getNombreCientifico());

       if (verAnimal.isPresent()) {
           throw new RuntimeException("Ya existe un animal con el nombre cientifico que intentas ingresar" + animal.getNombreCientifico());
       }else{
       
       return animalRepository.save(animal);
       }

   }
 
 public  Optional<AnimalModel> obtenerConNombre_cientifico(String nombreCientifico){ // Obtiene un animal por su nombre cientifico
   
   Optional<AnimalModel> verAnimal = this.animalRepository.findByNombreCientifico(nombreCientifico);
   
        if (verAnimal.isPresent()) {
          
            return verAnimal;
        }
        else{
        throw new RuntimeException("Datos incorrectos");
        }

   }
 
  public  Optional<AnimalModel> obtenerConNombre_comun(String nombreComun){
   
   Optional<AnimalModel> verAnimal = this.animalRepository.findByNombreComun(nombreComun);
   
        if (verAnimal.isPresent()) {
          
            return verAnimal;
        }
        else{
        throw new RuntimeException("Datos incorrectos");
        }

   }
  
  public ArrayList<AnimalModel> obtenerPorRiesgo(Integer riesgo){
   
   return animalRepository.findByRiesgo(riesgo);
   }

  
}

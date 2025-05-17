package com.example.PruebaSesion.controllers;

import com.example.PruebaSesion.models.ReglasModel;
import com.example.PruebaSesion.service.ReglasService;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

@Controller //Esto es un controlador de Spring MVC
public class ReglasController {

@Autowired //instancio el servicio de reglas
private ReglasService reglasService; //Inyección de dependencias de la clase ReglasService

    @GetMapping("/reglas")
    public String obtenerReglas(Model model) {
        ArrayList<ReglasModel> listaReglas = this.reglasService.obtenerReglas();
        model.addAttribute("listaReglas", listaReglas); // Pasamos la lista al modelo
        return "reglas"; // Nombre del JSP (sin .jsp)
    }


    @GetMapping("/reglas/{id}") //El url para este método es /reglas/{numeroRegla}, el numeroRegla es un parámetro que se pasa por la url
    public String getIdRegla(@PathVariable Long id , Model model) { //Busca la regla por su id y la devuelve en un objeto de tipo Model para que se pueda usar en la vista
        Optional<ReglasModel> regla = this.reglasService.obtenerPorId(id); //Obtengo la regla a partir del id que me pasan por la url, el get() es para obtener el valor del Optional que devuelve el servicio viene de servicios el obtenerPorId
        
        if (regla.isPresent()) { //Si la regla existe
            model.addAttribute("regla", regla.get().getDescripcionRegla()); //Agrego la regla al modelo para que se pueda acceder a ella desde la vista, el nombre "regla" es el que se va a usar en la vista para acceder al objeto regla
            return "reglas"; //Este "regla" es de la vista que se va a cargar, no del modelo  
        } 

            model.addAttribute("error", "La regla con ID" + id + "no fue econtrada"); //Agrego la regla al modelo para que se pueda acceder a ella desde la vista, el nombre "regla" es el que se va a usar en la vista para acceder al objeto regla
            return "error"; //Este "regla" es de la vista que se va a cargar, no del modelo

       
        }



    }


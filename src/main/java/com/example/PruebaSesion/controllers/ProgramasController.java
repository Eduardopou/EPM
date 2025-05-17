package com.example.PruebaSesion.controllers;

import com.example.PruebaSesion.models.ProgramasModel;
import com.example.PruebaSesion.service.ProgramasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ProgramasController {

    @Autowired // Instancio el servicio de programas.
    private ProgramasService programasService;

    @GetMapping("/programa")
    public String obtenerProgramas(Model model){
        ArrayList<ProgramasModel> listaProgramas = this.programasService.obtenerProgramas();
        model.addAttribute("listaProgramas", listaProgramas); // Pasamos la lista al modelo.
        return "programas"; // JSP: programas.jsp
    }

    @GetMapping("/programa/{id}") // URL con ID del programa
    public String getIdPrograma(@PathVariable Long id, Model model){
        Optional<ProgramasModel> programa = this.programasService.obtenerPorId(id);

        if (programa.isPresent()) {
            model.addAttribute("programa", programa.get()); // Agregamos el objeto completo al modelo
            return "programas";
        }

        model.addAttribute("error", "El programa con ID " + id + " no existe.");
        return "error";
    }
}

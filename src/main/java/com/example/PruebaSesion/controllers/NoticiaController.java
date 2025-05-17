package com.example.PruebaSesion.controllers;


import com.example.PruebaSesion.models.NoticiaModel;
import com.example.PruebaSesion.service.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Optional;

@Controller
public class NoticiaController {
@Autowired
NoticiaService noticiaService;

    @GetMapping("/noticias")
    public String mostrarInicio( Model model) {


        ArrayList<NoticiaModel> noticias = this.noticiaService.obtenerNoticias();
        model.addAttribute("noticias", noticias);
        return "noticias";
    }


    @PostMapping("/agregarNoticia")
    public String agregarNoticia(@ModelAttribute NoticiaModel noticia) {
        noticiaService.guardarNoticia(noticia);
        return "redirect:/";
    }





}

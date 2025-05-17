package com.example.PruebaSesion.controllers;

import com.example.PruebaSesion.models.InvestigacionesModel;
import com.example.PruebaSesion.service.InvestigacionesService;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;


@Controller
public class InvestigacionesController {

    @Autowired //Insancio el servicio de investigaciones.
    private InvestigacionesService investigacionesService;

    @GetMapping("/investigaciones")
    public String obtenerInvestigaciones(Model model){
        ArrayList<InvestigacionesModel> listaInvestigaciones = this.investigacionesService.obtenerInvestigaciones();
        model.addAttribute("listaInvestigaciones", listaInvestigaciones); //Pasamos la lista al modelo.
        return "investigaciones";
    }



}

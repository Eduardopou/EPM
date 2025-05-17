package com.example.PruebaSesion.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.PruebaSesion.controllers.request.AnimalFavoritoDTO;
import com.example.PruebaSesion.models.AnimalModel;
import com.example.PruebaSesion.models.UserEntity;
import com.example.PruebaSesion.models.relations.UsuarioAnimalId;
import com.example.PruebaSesion.repositories.UsuarioAnimalRepository;
import com.example.PruebaSesion.security.CustomUserDetails;
import com.example.PruebaSesion.service.AnimalService;
import com.example.PruebaSesion.service.UsuarioAnimalService;
import com.example.PruebaSesion.service.UsuarioServicio;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class AnimalController {
@Autowired
private AnimalService animalService;

@Autowired
private UsuarioServicio usuarioServicio;

    @Autowired
    private UsuarioAnimalService usuarioAnimalService;

    @Autowired
    private UsuarioAnimalRepository usuarioAnimalRepository;

    @ModelAttribute("animalFavorito")
    public AnimalFavoritoDTO agregarAnimalFavorito() {return new AnimalFavoritoDTO();}


    
     @GetMapping("/animales")
    public String listarAnimales(Model model) { 
        List<AnimalModel> listaAnimales = animalService.obtenerAnimales();
        model.addAttribute("animales", listaAnimales); // Pasamos la lista al modelo
        return "animales";
    }

    @PostMapping("/animales/agregar")
    public String agregarFavorito(@ModelAttribute("animalFavorito") AnimalFavoritoDTO favoritoForm,
                                  RedirectAttributes redirectAttributes, Authentication authentication) {

        CustomUserDetails usuario = (CustomUserDetails) authentication.getPrincipal();
        String username = usuario.getUsername();
        UserEntity user = usuarioServicio.buscarPorEmail(username);
        Long idUser = user.getId();

        Long animalId = favoritoForm.getAnimalId();
        UsuarioAnimalId id = new UsuarioAnimalId(idUser, animalId);

        if (usuarioAnimalRepository.existsById(id)) {
            redirectAttributes.addFlashAttribute("error", "¡El animal ya está en tus favoritos!");
            return "redirect:/animales";
        }

        usuarioAnimalService.agregarFavorito(idUser, animalId);
        redirectAttributes.addFlashAttribute("success", "¡Agregado a favoritos!");
        return "redirect:/animales";
    }


    @GetMapping("/animales/enviar")
    public String mostrarInfoAnimal(@RequestParam("id") Long id, Model model) {
        Optional<AnimalModel> optionalAnimal = animalService.findAnimalById(id);

        if (optionalAnimal.isPresent()) {
            AnimalModel animal = optionalAnimal.get();
            model.addAttribute("animalInfo", animal); // ahora sí se renderiza bien
        } else {
            model.addAttribute("error", "No se encontró el animal con ID: " + id);
        }

        return "animales";
    }








}

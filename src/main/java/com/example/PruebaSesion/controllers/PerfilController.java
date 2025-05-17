package com.example.PruebaSesion.controllers;

import com.example.PruebaSesion.controllers.request.AnimalFavoritoDTO;
import com.example.PruebaSesion.controllers.request.CreateUserDTO;
import com.example.PruebaSesion.exceptions.UsuarioYaExisteException;
import com.example.PruebaSesion.models.AnimalModel;
import com.example.PruebaSesion.models.UserEntity;
import com.example.PruebaSesion.repositories.UserRepository;
import com.example.PruebaSesion.repositories.UsuarioAnimalRepository;
import com.example.PruebaSesion.security.CustomUserDetails;
import com.example.PruebaSesion.security.CustomUserDetailsService;
import com.example.PruebaSesion.service.UsuarioAnimalService;
import com.example.PruebaSesion.service.UsuarioServicio;
import com.example.PruebaSesion.service.UsuarioServicioImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/perfil")
public class PerfilController {
    @Autowired
    UsuarioServicioImpl usuarioService;
    @Autowired
     UsuarioServicio usuarioServicio;
    @Autowired
    UsuarioAnimalService usuarioAnimalService;
    @Autowired
    private UsuarioAnimalRepository usuarioAnimalRepository;


    @GetMapping
    public String mostrarPerfil(Model model, Authentication authentication) {

        //Obtener el user autenticado
         CustomUserDetails usuario= (CustomUserDetails) authentication.getPrincipal();
         String username = usuario.getUsername();
         //Obtener la entidad completa
         UserEntity user = usuarioServicio.buscarPorEmail(username);

         //Obtener lista de favoritos
        List<AnimalModel> listaFavoritos = usuarioAnimalService.obtenerFavoritos(user.getId());

        model.addAttribute("listaFavoritos", listaFavoritos);
         model.addAttribute("usuario", user);
        return "perfil";
    }

    @ModelAttribute("usuario")
    public CreateUserDTO retornarNuevoUsuarioRegistroDTO() {
        return new CreateUserDTO();
    }

    @PostMapping("/actualizar")
    public String actualizarPerfil(
            @ModelAttribute("usuario") CreateUserDTO usuarioActualizado,
            BindingResult result,
            Authentication authentication,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "perfil";
        }

        try {
            usuarioService.actualizarUsuario(authentication.getName(), usuarioActualizado);
            redirectAttributes.addFlashAttribute("exito", "Perfil actualizado correctamente");
        } catch (UsuarioYaExisteException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/perfil";
    }

    @ModelAttribute("animalFavorito")
    public AnimalFavoritoDTO agregarAnimalFavorito() {return new AnimalFavoritoDTO();}

    @PostMapping("/eliminarFavorito")
    public String eliminarFavorito(@ModelAttribute("animalFavorito") AnimalFavoritoDTO favoritoForm,
                                   RedirectAttributes redirectAttributes, Authentication authentication) {

        CustomUserDetails usuario = (CustomUserDetails) authentication.getPrincipal();
        String username = usuario.getUsername();
        UserEntity user = usuarioServicio.buscarPorEmail(username);
        Long idUser = user.getId();
        Long animalId = favoritoForm.getAnimalId();
        usuarioAnimalService.eliminarFavorito(idUser, animalId);
        if (!usuarioAnimalRepository.existsByIdUsuarioIdAndIdAnimalId(idUser, animalId)) {

            redirectAttributes.addFlashAttribute("exito", "Favorito eliminado");
        }

        return "redirect:/perfil";


    }

}
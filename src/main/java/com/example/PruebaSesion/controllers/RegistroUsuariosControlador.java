package com.example.PruebaSesion.controllers;

import com.example.PruebaSesion.controllers.request.CreateUserDTO;
import com.example.PruebaSesion.service.EmailService;
import com.example.PruebaSesion.service.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/registro")
public class RegistroUsuariosControlador {

    private final UsuarioServicio usuarioServicio;
    private final EmailService emailService;

    public RegistroUsuariosControlador(UsuarioServicio usuarioServicio, EmailService emailService) {
        this.usuarioServicio = usuarioServicio;
        this.emailService = emailService;
    }




    @ModelAttribute("usuario")
    public CreateUserDTO retornarNuevoUsuarioRegistroDTO() {
        return new CreateUserDTO();
    }

    @GetMapping
    public String mostrarFormularioDeRegistro() {
        return "registro";
    }

    @PostMapping
    public String registrarCuentaDeUsuario(@ModelAttribute("usuario") CreateUserDTO registroDTO) {
        usuarioServicio.guardar(registroDTO);
        emailService.enviarCorreoBienvenida(registroDTO.getEmail(), registroDTO.getNombre());
        return "redirect:/registro?exito";
    }
}
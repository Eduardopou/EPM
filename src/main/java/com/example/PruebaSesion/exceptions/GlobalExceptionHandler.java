package com.example.PruebaSesion.exceptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    //Esta clase va a controlar todas o la mayoria de excepciones de la aplicacion. Las clases de cada
    //excepcion estan en la misma carpeta

    // Excepción personalizada: Usuario ya existe
    @ExceptionHandler(UsuarioYaExisteException.class)
    public String handleUsuarioYaExiste(UsuarioYaExisteException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", ex.getMessage());
        return "redirect:/registro?error";
    }

    // Otras excepciones genéricas
    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception ex, Model model) {
        model.addAttribute("error", "Ocurrió un error inesperado: " + ex.getMessage());
        return "error"; // redirige a error.html
    }
}
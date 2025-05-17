package com.example.PruebaSesion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarCorreoBienvenida(String destinatario, String nombreUsuario) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(destinatario);
        mensaje.setSubject("¡Bienvenido a Santa Elena!");
        mensaje.setText("Hola " + nombreUsuario + ",\n\nGracias por registrarte. ¡Estamos felices de tenerte con nosotros!\n\nSaludos,\nEl equipo");

        mailSender.send(mensaje);
    }
}

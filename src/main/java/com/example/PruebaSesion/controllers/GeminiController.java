package com.example.PruebaSesion.controllers;

import com.example.PruebaSesion.service.GeminiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/gemini")
public class GeminiController {

    private final GeminiService geminiService;

    public GeminiController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @PostMapping("/generar")
    public ResponseEntity<String> generarTexto(@RequestBody Map<String, String> body) {
        String prompt = body.get("prompt");
        String respuesta = geminiService.generarTexto(prompt);
        return ResponseEntity.ok(respuesta);
    }
}
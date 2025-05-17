package com.example.PruebaSesion.controllers.request;

import lombok.Data;

@Data
public class AnimalFavoritoDTO {
    private Long usuarioId;
    private Long animalId;

    // Getters y setters (o usa @Data de Lombok)
}

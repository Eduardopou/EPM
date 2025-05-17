package com.example.PruebaSesion.models.relations;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class UsuarioAnimalId implements Serializable {

    private Long usuarioId;
    private Long animalId;

    public UsuarioAnimalId() {

    }


    public UsuarioAnimalId(Long usuarioId, Long animalId) {
        this.usuarioId = usuarioId;
        this.animalId = animalId;
    }

}

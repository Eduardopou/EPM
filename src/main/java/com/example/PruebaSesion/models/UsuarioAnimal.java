package com.example.PruebaSesion.models;

import com.example.PruebaSesion.models.relations.UsuarioAnimalId;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario_animal")
public class UsuarioAnimal {

    @EmbeddedId
    private UsuarioAnimalId id;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id")
    private UserEntity usuario;

    @ManyToOne
    @MapsId("animalId")
    @JoinColumn(name = "animal_id")
    private AnimalModel animal;

    public UsuarioAnimal(UsuarioAnimalId usuarioAnimalId) {
        this.id = usuarioAnimalId;
    }


    public UsuarioAnimal() {

    }
}

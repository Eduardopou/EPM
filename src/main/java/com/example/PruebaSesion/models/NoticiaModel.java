package com.example.PruebaSesion.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
@Data
@Entity
@Table(name = "noticias")
public class NoticiaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @NotNull(message = "El título no puede estar vacío")
    @Size(min = 2, max = 50, message = "El título debe tener entre 2 y 50 caracteres")
    private String titulo;

    @NotNull(message = "La descripción no puede estar vacía")
    @Size(min = 2, max = 2000, message = "La descripción debe tener entre 2 y 2000 caracteres")
    private String descripcion;

    private String imagen;

    private String url;

    @NotNull(message = "La fecha de publicación no puede estar vacía")
    private LocalDate fechaPublicacion;

    @NotNull(message = "El tipo de noticia no puede estar vacío")
    private String tipo;

}

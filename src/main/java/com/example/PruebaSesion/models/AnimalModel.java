package com.example.PruebaSesion.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data //Con data generamos los setters and getters
@Entity
@Table(name = "animal")
@NoArgsConstructor
@AllArgsConstructor
//Con esta etiqueta creamos automaticamente La base de datos y asi aseguramos evitar errores a la hora de mapear.
public class AnimalModel {
    
 // Definición de la clave primaria con la anotación @Id
    @Id //Especificamos que esta variable es de tipo Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Con esta etiqueta aseguramos que se genere automaticamente el id
    @Column(unique = true, nullable = false) //Con esta etiqueta aseguramos que es unico y que no puede ser nulo
    private Long idAnimal;
    
    @NotNull(message = "El nombre cientifico no puede estar vacío")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombreCientifico;
 
    @NotNull(message = "El nombre cientifico no puede estar vacío")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombreComun;
    
    private int riesgo;

    private String informacion;

    private String imagen;


}

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
@Data
@Entity
@Table(name = "reglas")
@NoArgsConstructor
@AllArgsConstructor
//Con esta etiqueta creamos o usamos automaticamente La tabla en la base de datos y asi aseguramos evitar errores a la hora de mapear.
public class ReglasModel {
    
    @Id //Especificamos que esta variable es de tipo Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Con esta etiqueta aseguramos que se genere automaticamente el id
    private Long idRegla; // Son variables que se usan para guardar los datos de la regla

    @NotNull(message = "El numero de regla no puede estar vacío") //Con esta etiqueta aseguramos que no sea nulo
    private int numeroRegla;

    @NotNull(message = "El numero de regla no puede estar vacío")//error millo
    private String descripcionRegla;

    

}

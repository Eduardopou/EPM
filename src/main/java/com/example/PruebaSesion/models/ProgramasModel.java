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
@Table(name = "programas")
@NoArgsConstructor
@AllArgsConstructor // Esta anotación indica que esta clase se mapeará a la tabla subprogramas en la base de datos
public class ProgramasModel {

    @Id // Indica que este campo es la clave primaria de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // La base de datos generará automáticamente el ID
    public Long id; // Identificador único para cada programa

    @NotNull(message = "El número de programa no puede estar vacío") // Este campo no puede ser nulo
    public String tipoPrograma;

    @NotNull(message = "El nombre del programa no puede estar vacío") // Validación para evitar nulos
    public String nombre;

    @NotNull(message = "La descripcion del programa no puede estar vacia")
    @Size(min = 2, max = 10000, message = "El nombre debe tener entre 2 y 10000 caracteres")
    public String descripcion;


}

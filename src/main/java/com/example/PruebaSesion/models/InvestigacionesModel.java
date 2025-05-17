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

import java.sql.Date;

@Data
@Builder
@Entity
@Table(name = "investigaciones")
@NoArgsConstructor
@AllArgsConstructor //Usamos la tabla investigaciones de nuestra base de datos.
public class InvestigacionesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Va a generar el ID.
    private Long idInvestigacion;

    @NotNull(message = "El nombre del investigador no puede ir vacio.")
    @Size(min = 2, max = 150, message = "El nombre debe tener entre 2 y 150 caracteres.")
    private String nombreInvestigador;

    @NotNull
    private Date fechaInicio;

    @NotNull
    private Date fechaFin;



}

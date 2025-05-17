package com.example.PruebaSesion.service;

import java.util.ArrayList;
import java.util.Optional;

import com.example.PruebaSesion.models.InvestigacionesModel;
import com.example.PruebaSesion.repositories.InvestigacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvestigacionesService { //Esta clase se encargara de obtener las reglas de la base de datos.

    @Autowired
    InvestigacionesRepository investigacionesRepository; //Creamos el objeto para poder usar los metodos.

    public ArrayList<InvestigacionesModel> obtenerInvestigaciones(){ //Obtiene todas las investigaciones de la base de datos en un arraylist.

        return (ArrayList<InvestigacionesModel>) investigacionesRepository.findAll(); //Devuelve un arraylist con todas las investigaciones de la base de datos.
    }

    public Optional<InvestigacionesModel> obtenerConIdInvestigacion (Long idInvestigacion){

        return investigacionesRepository.findByIdInvestigacion(idInvestigacion);
    }

    public Optional<InvestigacionesModel> obtenerConNombreInvestigador (String nombreInvestigador){

        return investigacionesRepository.findByNombreInvestigador(nombreInvestigador);
    }
}

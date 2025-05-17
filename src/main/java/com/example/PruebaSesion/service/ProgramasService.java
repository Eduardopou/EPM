package com.example.PruebaSesion.service;

import java.util.ArrayList;
import java.util.Optional;

import com.example.PruebaSesion.models.ProgramasModel;
import com.example.PruebaSesion.repositories.ProgramasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgramasService {

    @Autowired
    ProgramasRepository programasRepository;

    // Obtener todos los programas
    public ArrayList<ProgramasModel> obtenerProgramas() {
        return (ArrayList<ProgramasModel>) programasRepository.findAll();
    }

    // Obtener programa por nombre
    public Optional<ProgramasModel> obtenerPorNombre(String nombre) {
        Optional<ProgramasModel> programa = programasRepository.findByNombre(nombre);

        if (programa.isPresent()) {
            return programa;
        } else {
            throw new RuntimeException("Programa no encontrado con ese nombre.");
        }
    }

    // Obtener programa por ID
    public Optional<ProgramasModel> obtenerPorId(Long idPrograma) {

        return programasRepository.findById(idPrograma);
    }
}

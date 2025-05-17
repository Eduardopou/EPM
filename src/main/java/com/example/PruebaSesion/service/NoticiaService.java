package com.example.PruebaSesion.service;

import com.example.PruebaSesion.models.NoticiaModel;
import com.example.PruebaSesion.repositories.NoticiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class NoticiaService {
    @Autowired
    NoticiaRepository noticiaRepository;

    public ArrayList<NoticiaModel> obtenerNoticias(){

      return (ArrayList<NoticiaModel>) noticiaRepository.findAll();

    }

    public NoticiaModel guardarNoticia(NoticiaModel noticia){

        Optional<NoticiaModel> verNoticia = this.noticiaRepository.findById(noticia.getId());

        if(verNoticia.isPresent()){
            throw new RuntimeException("La noticia ya existe");

        }else{
            return this.noticiaRepository.save(noticia);

        }
    }

    public Optional<NoticiaModel> obtenerNoticiaPorId(Long id){

        return this.noticiaRepository.findById(id);

    }


}

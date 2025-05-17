package com.example.PruebaSesion.service;

import com.example.PruebaSesion.models.AnimalModel;
import com.example.PruebaSesion.models.UserEntity;
import com.example.PruebaSesion.models.UsuarioAnimal;
import com.example.PruebaSesion.models.relations.UsuarioAnimalId;
import com.example.PruebaSesion.repositories.AnimalRepository;
import com.example.PruebaSesion.repositories.UserRepository;
import com.example.PruebaSesion.repositories.UsuarioAnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UsuarioAnimalService {

    private final UsuarioAnimalRepository usuarioAnimalRepository;

    private final UserRepository usuarioRepository;

    private final AnimalRepository animalRepository;
    private final AnimalService animalService;

    public UsuarioAnimalService(UsuarioAnimalRepository usuarioAnimalRepository, UserRepository usuarioRepository, AnimalRepository animalRepository, AnimalService animalService) {
        this.usuarioAnimalRepository = usuarioAnimalRepository;
        this.usuarioRepository = usuarioRepository;
        this.animalRepository = animalRepository;
        this.animalService = animalService;
    }

    public void agregarFavorito(Long usuarioId, Long animalId) {
        UserEntity usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        AnimalModel animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new RuntimeException("Animal no encontrado"));

        // Crear clave compuesta
        UsuarioAnimalId id = new UsuarioAnimalId(usuarioId, animalId);

        // Verificar que no exista
        if (usuarioAnimalRepository.existsById(id)) {
            throw new RuntimeException("Ya está en favoritos");
        }

        UsuarioAnimal favorito = new UsuarioAnimal(new UsuarioAnimalId(usuarioId, animalId));
        favorito.setUsuario(usuario);
        favorito.setAnimal(animal);

        usuarioAnimalRepository.save(favorito);
    }

    public List<AnimalModel> obtenerFavoritos(Long usuarioId) {

        List<UsuarioAnimal> favoritos = usuarioAnimalRepository.findByUsuarioId(usuarioId);

        List<AnimalModel> listaAnimales = new ArrayList<>();

        for (UsuarioAnimal favorito : favoritos) {
            listaAnimales.add(favorito.getAnimal());
        }

        return listaAnimales;
    }

    @Transactional
    public void eliminarFavorito(Long usuarioId, Long animalId) {
        usuarioAnimalRepository.deleteByIdUsuarioIdAndIdAnimalId(usuarioId, animalId);

    }


}

package com.example.PruebaSesion.repositories;

import java.util.ArrayList;
import java.util.Optional;

import com.example.PruebaSesion.models.NoticiaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface NoticiaRepository extends JpaRepository<NoticiaModel, Long> {


}

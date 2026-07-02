package com.example.catalogoservice.repository;

import com.example.catalogoservice.model.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>, JpaSpecificationExecutor<Categoria> {

    // Validación de unicidad de nombre para evitar categorías duplicadas.
    // Se apoya en el índice único sobre 'nombre' definido en la entidad.
    boolean existsByNombre(String nombre);

    Optional<Categoria> findByNombre(String nombre);
}

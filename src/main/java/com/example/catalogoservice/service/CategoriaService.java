package com.example.catalogoservice.service;

import com.example.catalogoservice.model.dto.CategoriaCreateDTO;
import com.example.catalogoservice.model.dto.CategoriaResponseDTO;
import com.example.catalogoservice.model.dto.CategoriaUpdateDTO;
import com.example.catalogoservice.model.enums.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoriaService {

    CategoriaResponseDTO createCategoria(CategoriaCreateDTO dto);

    CategoriaResponseDTO getCategoria(Long id);

    Page<CategoriaResponseDTO> getCategorias(String nombre, State state, Pageable pageable);

    CategoriaResponseDTO updateCategoria(Long id, CategoriaUpdateDTO dto);

    void deleteCategoria(Long id);

    CategoriaResponseDTO changeState(Long id, State newState);
}
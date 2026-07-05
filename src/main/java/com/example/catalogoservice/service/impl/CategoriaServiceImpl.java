package com.example.catalogoservice.service.impl;

import com.example.catalogoservice.exception.ResourceNotFoundException;
import com.example.catalogoservice.mapper.CategoriaMapper;
import com.example.catalogoservice.model.dto.CategoriaCreateDTO;
import com.example.catalogoservice.model.dto.CategoriaResponseDTO;
import com.example.catalogoservice.model.dto.CategoriaUpdateDTO;
import com.example.catalogoservice.model.entity.Categoria;
import com.example.catalogoservice.model.enums.State;
import com.example.catalogoservice.repository.CategoriaRepository;
import com.example.catalogoservice.service.CategoriaService;
import com.example.catalogoservice.specification.CategoriaSpecifications;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    @Override
    @Transactional
    public CategoriaResponseDTO createCategoria(CategoriaCreateDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("El DTO de creación no puede ser nulo");
        }
        log.info("Intento de creación de categoría con nombre: {}", dto.nombre());

        if (categoriaRepository.existsByNombre(dto.nombre())) {
            throw new ResourceNotFoundException("Ya existe una categoría con el nombre: " + dto.nombre());
        }

        Categoria categoria = categoriaMapper.toEntity(dto);
        Categoria saved = categoriaRepository.save(categoria);

        log.info("Categoría creada exitosamente con ID: {}", saved.getId());
        return categoriaMapper.toResponseDto(saved);
    }

    @Override
    public CategoriaResponseDTO getCategoria(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }
        log.info("Buscando categoría con ID: {}", id);
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        return categoriaMapper.toResponseDto(categoria);
    }

    @Override
    public Page<CategoriaResponseDTO> getCategorias(String nombre, State state, Pageable pageable) {
        if (pageable == null) {
            throw new IllegalArgumentException("El parámetro Pageable no puede ser nulo");
        }
        log.info("Búsqueda de categorías con filtros dinámicos");

        Specification<Categoria> spec = Specification
                .where(CategoriaSpecifications.hasNombre(nombre))
                .and(CategoriaSpecifications.hasState(state));

        return categoriaRepository.findAll(spec, pageable)
                .map(categoriaMapper::toResponseDto);
    }

    @Override
    @Transactional
    public CategoriaResponseDTO updateCategoria(Long id, CategoriaUpdateDTO dto) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }
        if (dto == null) {
            throw new IllegalArgumentException("El DTO de actualización no puede ser nulo");
        }
        log.info("Intento de actualización de categoría con ID: {}", id);

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        if (categoria.getState() == State.DELETED) {
            throw new IllegalStateException("No se puede editar una categoría eliminada");
        }

        categoriaMapper.updateEntityFromDto(dto, categoria);
        Categoria updated = categoriaRepository.save(categoria);

        log.info("Categoría actualizada exitosamente con ID: {}", updated.getId());
        return categoriaMapper.toResponseDto(updated);
    }

    @Override
    @Transactional
    public void deleteCategoria(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }
        log.info("Intento de eliminación de categoría con ID: {}", id);

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        if (categoria.getState() == State.DELETED) {
            throw new IllegalStateException("La categoría ya se encuentra eliminada");
        }

        categoria.setState(State.DELETED);
        categoriaRepository.save(categoria);

        log.info("Categoría eliminada correctamente con ID: {}", id);
    }

    @Override
    @Transactional
    public CategoriaResponseDTO changeState(Long id, State newState) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }
        if (newState == null) {
            throw new IllegalArgumentException("El nuevo estado no puede ser nulo");
        }
        log.info("Intento de cambio de estado de categoría con ID: {} a {}", id, newState);

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        categoria.setState(newState);
        categoriaRepository.save(categoria);

        log.info("Estado de categoría ID: {} actualizado a {}", id, newState);
        return categoriaMapper.toResponseDto(categoria);
    }
}
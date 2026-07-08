package com.example.catalogoservice.service.impl;

import com.example.catalogoservice.exception.DuplicateResourceException;
import com.example.catalogoservice.exception.ResourceNotFoundException;
import com.example.catalogoservice.mapper.ProductoMapper;
import com.example.catalogoservice.model.dto.ProductoAdminResponseDTO;
import com.example.catalogoservice.model.dto.ProductoCreateDTO;
import com.example.catalogoservice.model.dto.ProductoResponseDTO;
import com.example.catalogoservice.model.dto.ProductoUpdateDTO;
import com.example.catalogoservice.model.entity.Categoria;
import com.example.catalogoservice.model.entity.Producto;
import com.example.catalogoservice.model.enums.State;
import com.example.catalogoservice.repository.CategoriaRepository;
import com.example.catalogoservice.repository.ProductoRepository;
import com.example.catalogoservice.service.ProductoService;
import com.example.catalogoservice.specification.ProductoSpecifications;
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
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProductoMapper productoMapper;

    @Override
    @Transactional
    public ProductoResponseDTO createProducto(ProductoCreateDTO dto) {
        log.info("Intento de creación de producto con nombre: {}", dto.nombre());

        if (productoRepository.existsByNombre(dto.nombre())) {
            throw new DuplicateResourceException("Ya existe un producto con el nombre: " + dto.nombre());
        }

        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con ID: " + dto.categoriaId()));

        Producto producto = productoMapper.toEntity(dto);
        producto.setCategoria(categoria);

        Producto saved = productoRepository.save(producto);

        log.info("Producto creado exitosamente con ID: {}", saved.getId());
        return productoMapper.toResponseDto(saved);
    }

    @Override
    public ProductoResponseDTO getProducto(Long id) {
        log.info("Buscando producto con ID: {}", id);
        Producto producto = productoRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        return productoMapper.toResponseDto(producto);
    }

    @Override
    public ProductoAdminResponseDTO getProductoAdmin(Long id) {
        log.info("Buscando producto (admin) con ID: {}", id);
        Producto producto = productoRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        return productoMapper.toAdminResponseDto(producto);
    }

    @Override
    public Page<ProductoResponseDTO> getProductos(String nombre, Long categoriaId, State state, Pageable pageable) {
        if (pageable == null) {
            throw new IllegalArgumentException("El parámetro Pageable no puede ser nulo");
        }
        log.info("Búsqueda de productos con filtros dinámicos");

        Specification<Producto> spec = Specification
                .where(ProductoSpecifications.hasNombre(nombre))
                .and(ProductoSpecifications.hasCategoria(categoriaId))
                .and(ProductoSpecifications.hasState(state));

        return productoRepository.findAll(spec, pageable)
                .map(productoMapper::toResponseDto);
    }

    @Override
    @Transactional
    public ProductoResponseDTO updateProducto(Long id, ProductoUpdateDTO dto) {
        log.info("Intento de actualización de producto con ID: {}", id);

        Producto producto = productoRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        if (producto.getState() == State.DELETED) {
            throw new IllegalStateException("No se puede editar un producto eliminado");
        }

        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con ID: " + dto.categoriaId()));

        productoMapper.updateEntityFromDto(dto, producto);
        producto.setCategoria(categoria);

        Producto updated = productoRepository.save(producto);

        log.info("Producto actualizado exitosamente con ID: {}", updated.getId());
        return productoMapper.toResponseDto(updated);
    }

    @Override
    @Transactional
    public void deleteProducto(Long id) {
        log.info("Intento de eliminación de producto con ID: {}", id);

        Producto producto = productoRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        if (producto.getState() == State.DELETED) {
            throw new IllegalStateException("El producto ya se encuentra eliminado");
        }

        producto.setState(State.DELETED);
        productoRepository.save(producto);

        log.info("Producto eliminado correctamente con ID: {}", id);
    }

    @Override
    @Transactional
    public ProductoResponseDTO changeState(Long id, State newState) {
        if (newState == null) {
            throw new IllegalArgumentException("El nuevo estado no puede ser nulo");
        }
        log.info("Intento de cambio de estado de producto con ID: {} a {}", id, newState);

        Producto producto = productoRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        producto.setState(newState);
        productoRepository.save(producto);

        log.info("Estado de producto ID: {} actualizado a {}", id, newState);
        return productoMapper.toResponseDto(producto);
    }
}
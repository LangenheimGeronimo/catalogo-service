package com.example.catalogoservice.service;

import com.example.catalogoservice.model.dto.ProductoAdminResponseDTO;
import com.example.catalogoservice.model.dto.ProductoCreateDTO;
import com.example.catalogoservice.model.dto.ProductoResponseDTO;
import com.example.catalogoservice.model.dto.ProductoUpdateDTO;
import com.example.catalogoservice.model.enums.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductoService {

    ProductoResponseDTO createProducto(ProductoCreateDTO dto);

    ProductoResponseDTO getProducto(Long id);

    Page<ProductoResponseDTO> getProductos(String nombre, Long categoriaId, State state, Pageable pageable);

    ProductoResponseDTO updateProducto(Long id, ProductoUpdateDTO dto);

    void deleteProducto(Long id);

    ProductoResponseDTO changeState(Long id, State newState);

    ProductoAdminResponseDTO getProductoAdmin(Long id);
}

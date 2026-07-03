package com.example.catalogoservice.mapper;

import com.example.catalogoservice.model.dto.ProductoAdminResponseDTO;
import com.example.catalogoservice.model.dto.ProductoCreateDTO;
import com.example.catalogoservice.model.dto.ProductoResponseDTO;
import com.example.catalogoservice.model.dto.ProductoUpdateDTO;
import com.example.catalogoservice.model.entity.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ProductoMapper {

    @Mapping(target = "categoriaNombre", source = "categoria.nombre")
    ProductoResponseDTO toResponseDto(Producto producto);

    @Mapping(target = "categoriaNombre", source = "categoria.nombre")
    ProductoAdminResponseDTO toAdminResponseDto(Producto producto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Producto toEntity(ProductoCreateDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(ProductoUpdateDTO dto, @MappingTarget Producto entity);
}
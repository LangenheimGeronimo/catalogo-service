package com.example.catalogoservice.mapper;

import com.example.catalogoservice.model.dto.CategoriaCreateDTO;
import com.example.catalogoservice.model.dto.CategoriaResponseDTO;
import com.example.catalogoservice.model.dto.CategoriaUpdateDTO;
import com.example.catalogoservice.model.entity.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CategoriaMapper {

    CategoriaResponseDTO toResponseDto(Categoria categoria);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Categoria toEntity(CategoriaCreateDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(CategoriaUpdateDTO dto, @MappingTarget Categoria entity);
}
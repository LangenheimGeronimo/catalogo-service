package com.example.catalogoservice.model.dto;

import com.example.catalogoservice.model.enums.State;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

public record CategoriaResponseDTO(
    @Schema(description = "ID único de la categoría", example = "1")
    Long id,

    @Schema(description = "Nombre de la categoría", example = "Electrónica")
    String nombre,

    @Schema(description = "Descripción de la categoría", example = "Productos electrónicos y tecnología")
    String descripcion,

    @Schema(description = "Estado de la categoría", example = "ACTIVE")
    State state,

    @Schema(description = "Fecha de creación")
    LocalDateTime createdAt,

    @Schema(description = "Fecha de última modificación")
    LocalDateTime updatedAt
) {}
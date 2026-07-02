package com.example.catalogoservice.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaUpdateDTO(
    @Schema(description = "Nombre de la categoría", example = "Electrónica", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 50, message = "El nombre no puede superar los 50 caracteres")
    String nombre,

    @Schema(description = "Descripción de la categoría", example = "Productos electrónicos y tecnología")
    @Size(max = 255, message = "La descripción no puede superar los 255 caracteres")
    String descripcion
) {}
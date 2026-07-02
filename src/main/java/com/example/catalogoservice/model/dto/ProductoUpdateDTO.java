package com.example.catalogoservice.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record ProductoUpdateDTO(
    @Schema(description = "Nombre del producto", example = "Laptop Dell XPS", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    String nombre,

    @Schema(description = "Descripción del producto", example = "Laptop de alta gama")
    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    String descripcion,

    @Schema(description = "Precio de costo del producto", example = "500.00", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "El precio de costo es obligatorio")
    @Positive(message = "El precio de costo debe ser mayor a cero")
    BigDecimal precioCosto,

    @Schema(description = "Precio de venta del producto", example = "800.00", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "El precio de venta es obligatorio")
    @Positive(message = "El precio de venta debe ser mayor a cero")
    BigDecimal precioVenta,

    @Schema(description = "ID de la categoría del producto", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "La categoría es obligatoria")
    Long categoriaId
) {}
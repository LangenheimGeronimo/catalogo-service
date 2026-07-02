package com.example.catalogoservice.model.dto;

import com.example.catalogoservice.model.enums.State;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductoAdminResponseDTO(
    @Schema(description = "ID único del producto", example = "1")
    Long id,

    @Schema(description = "Nombre del producto", example = "Laptop Dell XPS")
    String nombre,

    @Schema(description = "Descripción del producto", example = "Laptop de alta gama")
    String descripcion,

    @Schema(description = "Precio de costo del producto", example = "500.00")
    BigDecimal precioCosto,

    @Schema(description = "Precio de venta del producto", example = "800.00")
    BigDecimal precioVenta,

    @Schema(description = "Categoría del producto", example = "Electrónica")
    String categoriaNombre,

    @Schema(description = "Estado del producto", example = "ACTIVE")
    State state,

    @Schema(description = "Fecha de creación")
    LocalDateTime createdAt,

    @Schema(description = "Fecha de última modificación")
    LocalDateTime updatedAt
) {}
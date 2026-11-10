package com.k4.poscampus.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record DetalleVentaDtoRequest(
        @Schema(description = "Ingresa el id de la venta",
        example = "1")
        @NotNull(message = "Error, el codigo de la venta no puede estar nulo")
        @Positive(message = "Error, el codigo de la venta debe ser positivo")
        Long ventaId,
        @NotNull(message = "Error, el codigo del producto no puede estar nulo")
        @Positive(message = "Error, el codigo del producto venta debe ser positivo")
        Long productoId,
        @NotNull(message = "Error,la cantidad no puede estar nulo")
        @Positive(message = "Error, la cantidad debe ser positivo")
        Integer cantidad,
        @NotNull(message = "Error, el subtotal no puede estar nulo")
        @Positive(message = "Error, el subtotal debe ser positivo")
        BigDecimal subtotal
) {
}

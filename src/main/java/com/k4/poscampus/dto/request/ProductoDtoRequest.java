package com.k4.poscampus.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductoDtoRequest(
        @NotBlank(message = "El nombre debe tener información")
        @Size(min = 2, max = 50, message = "El nombre debe estar en un rango de 2-50 caracteres")
        String nombre,
        @NotBlank(message = "La marca debe tener información")
        String marca,
        @NotNull(message = "Error, el stock no puede estar nulo")
        @PositiveOrZero(message = "Error, el stock debe ser positivo")
        Integer stock,
        @Positive(message = "Error, el precio de compra debe ser positivo")
        @NotNull(message = "Error, el precio de compra no puede ser nulo")
        BigDecimal precioCompra,
        @Positive(message = "Error, el precio de venta debe ser positivo")
        @NotNull(message = "Error, el precio de venta no puede ser nulo")
        BigDecimal precioVenta
) {
}

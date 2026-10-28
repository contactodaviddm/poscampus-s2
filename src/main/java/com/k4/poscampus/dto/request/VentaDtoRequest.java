package com.k4.poscampus.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record VentaDtoRequest(
        @NotNull(message = "Error, el documento no puede estar nulo")
        @Positive(message = "Error, el documento debe ser positivo")
        Long documentoCliente,
        @NotBlank(message = "El nombre debe tener información")
        @Size(min = 2, max = 150, message = "El nombre debe estar en un rango de 2 - 150 caracteres")
        String nombreCompleto,
        @NotNull(message = "Error, el total no puede estar nulo")
        @PositiveOrZero(message = "Error, el total debe ser positivo")
        BigDecimal total
) {
}

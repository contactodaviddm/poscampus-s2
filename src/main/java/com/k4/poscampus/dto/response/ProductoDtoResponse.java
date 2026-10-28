package com.k4.poscampus.dto.response;

import java.math.BigDecimal;

public record ProductoDtoResponse(
        Long id, String nombre, String marca, Long stock, BigDecimal precioCompra, BigDecimal precioVenta
) {
}

package com.k4.poscampus.dto.response;

import java.math.BigDecimal;

public record DetalleVentaDtoResponse (
        Long id,
        VentaDtoResponse venta,
        ProductoDtoResponseFactura producto,
        Integer cantidad,
        BigDecimal subtotal
){
}

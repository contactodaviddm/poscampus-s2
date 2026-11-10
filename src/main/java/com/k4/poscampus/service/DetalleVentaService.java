package com.k4.poscampus.service;


import com.k4.poscampus.dto.request.DetalleVentaDtoRequest;
import com.k4.poscampus.dto.response.DetalleVentaDtoResponse;

import java.math.BigDecimal;
import java.util.List;

public interface DetalleVentaService {
    DetalleVentaDtoResponse guardar(DetalleVentaDtoRequest dto);

    DetalleVentaDtoResponse actualizar(DetalleVentaDtoRequest dto, Long id);

    void eliminar(Long id);

    List<DetalleVentaDtoResponse> listar();

    List<DetalleVentaDtoResponse> buscarVentaId(Long id);

    List<DetalleVentaDtoResponse> buscarProductoId(Long id);

    List<DetalleVentaDtoResponse> filtrarProductoSubtotal(Long id, BigDecimal subtotal);

    DetalleVentaDtoResponse buscarId(Long id);
}

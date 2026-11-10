package com.k4.poscampus.service;

import com.k4.poscampus.dto.request.ProductoDtoRequest;
import com.k4.poscampus.dto.response.ProductoDtoResponse;
import com.k4.poscampus.model.Producto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductoService {
    ProductoDtoResponse guardar(ProductoDtoRequest dto);
    ProductoDtoResponse actualizar(ProductoDtoRequest dto, Long id);
    void eliminar(Long id);
    List<ProductoDtoResponse> listar();
    ProductoDtoResponse buscarId(Long id);
    List<ProductoDtoResponse> buscarMarca(String nombre);
}

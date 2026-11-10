package com.k4.poscampus.service.impl;

import com.k4.poscampus.dto.request.ProductoDtoRequest;
import com.k4.poscampus.dto.response.ProductoDtoResponse;
import com.k4.poscampus.mapper.ProductoMapper;
import com.k4.poscampus.model.Producto;
import com.k4.poscampus.repository.DetalleVentaRepository;
import com.k4.poscampus.repository.ProductoRepository;
import com.k4.poscampus.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductoServiceImpl implements ProductoService {
    private final ProductoMapper productoMapper;
    private final ProductoRepository productoRepository;
    private final DetalleVentaRepository detalleVentaRepository;

    @Override
    public ProductoDtoResponse guardar(ProductoDtoRequest dto) {
        //cast: convierto el dto en una entity
        Producto p = productoMapper.DtoToEntity(dto);
        Producto productoRespuesta = productoRepository.save(p);
        return productoMapper.entityToDto(productoRespuesta);
        //return productoMapper.entityToDto(productoRepository.save(productoMapper.DtoToEntity(dto)));
    }

    @Override
    public ProductoDtoResponse actualizar(ProductoDtoRequest dto, Long id) {
        Producto p = productoRepository.findById(id).orElseThrow(() -> new RuntimeException("Error, producto a actualizar no encontrado"));
        productoMapper.updateEntityFromDto(p, dto);
        Producto productoActualizado = productoRepository.save(p);
        return productoMapper.entityToDto(productoActualizado);
    }

    @Override
    public void eliminar(Long id) {
        Producto p = productoRepository.findById(id).orElseThrow(() -> new RuntimeException("El producto a eliminar no existe!"));
        if (detalleVentaRepository.existsByProductoId(p.getId()))
            throw new RuntimeException("Este producto pertenece a un detalle de venta!");
        productoRepository.delete(p);
    }

    @Override
    public List<ProductoDtoResponse> listar() {
        return productoRepository.findAll().stream().map(productoMapper::entityToDto).toList();
    }

    @Override
    public ProductoDtoResponse buscarId(Long id) {
        return productoMapper.entityToDto(productoRepository.findById(id).orElseThrow(() -> new RuntimeException("No existe dicho producto")));
    }

    @Override
    public List<ProductoDtoResponse> buscarMarca(String nombre) {
        return productoRepository.findByMarca(nombre).stream().map(productoMapper::entityToDto).toList();
    }
}

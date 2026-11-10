package com.k4.poscampus.service.impl;

import com.k4.poscampus.dto.request.VentaDtoRequest;
import com.k4.poscampus.dto.response.VentaDtoResponse;
import com.k4.poscampus.mapper.VentaMapper;
import com.k4.poscampus.model.Venta;
import com.k4.poscampus.repository.DetalleVentaRepository;
import com.k4.poscampus.repository.VentaRepository;
import com.k4.poscampus.service.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VentaServiceImpl implements VentaService {
    private final VentaMapper ventaMapper;
    private final VentaRepository ventaRepository;
    private final DetalleVentaRepository detalleVentaRepository;

    @Override
    public VentaDtoResponse guardar(VentaDtoRequest dto) {
        //cast: convierto el dto en una entity
        Venta v = ventaMapper.DtoToEntity(dto);
        Venta ventaRepuesta = ventaRepository.save(v);
        return ventaMapper.entityToDto(ventaRepuesta);
        //return productoMapper.entityToDto(productoRepository.save(productoMapper.DtoToEntity(dto)));
    }

    @Override
    public VentaDtoResponse actualizar(VentaDtoRequest dto, Long id) {
        Venta v = ventaRepository.findById(id).orElseThrow(() -> new RuntimeException("Error, venta a actualizar no encontrado"));
        ventaMapper.updateEntityFromDto(v, dto);
        Venta productoActualizado = ventaRepository.save(v);
        return ventaMapper.entityToDto(productoActualizado);
    }

    @Override
    public void eliminar(Long id) {
        Venta v = ventaRepository.findById(id).orElseThrow(() -> new RuntimeException("La venta a eliminar no existe!"));
        if (detalleVentaRepository.existsByVentaId(v.getId()))
            throw new RuntimeException("Esta venta esta ligada a un detalle de venta!");
        ventaRepository.delete(v);
    }

    @Override
    public List<VentaDtoResponse> listar() {
        return ventaRepository.findAll().stream().map(ventaMapper::entityToDto).toList();
    }

    @Override
    public VentaDtoResponse buscarId(Long id) {
        return ventaMapper.entityToDto(ventaRepository.findById(id).orElseThrow(() -> new RuntimeException("No existe dicha venta")));
    }
}

package com.k4.poscampus.service.impl;

import com.k4.poscampus.dto.request.DetalleVentaDtoRequest;
import com.k4.poscampus.dto.response.DetalleVentaDtoResponse;
import com.k4.poscampus.dto.response.ProductoDtoResponseFactura;
import com.k4.poscampus.dto.response.VentaDtoResponse;
import com.k4.poscampus.exception.DatabaseRuleException;
import com.k4.poscampus.exception.BusinessRuleException;
import com.k4.poscampus.mapper.DetalleVentaMapper;
import com.k4.poscampus.mapper.ProductoMapper;
import com.k4.poscampus.mapper.VentaMapper;
import com.k4.poscampus.model.DetalleVenta;
import com.k4.poscampus.model.Producto;
import com.k4.poscampus.model.Venta;
import com.k4.poscampus.repository.DetalleVentaRepository;
import com.k4.poscampus.repository.ProductoRepository;
import com.k4.poscampus.repository.VentaRepository;
import com.k4.poscampus.service.DetalleVentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {
    private final ProductoMapper productoMapper;
    private final ProductoRepository productoRepository;
    private final VentaRepository ventaRepository;
    private final VentaMapper ventaMapper;
    private final DetalleVentaMapper detalleVentaMapper;
    private final DetalleVentaRepository detalleVentaRepository;

    @Override
    public DetalleVentaDtoResponse guardar(DetalleVentaDtoRequest dto) {
        if (dto.cantidad()>250){
            throw new BusinessRuleException("Maximo 250 productos por detalle");
        }
        /*
        * DataAccessException.
        * PersistenceException.
        * RepositoryException.
        * */
        Venta v = ventaRepository.findById(dto.ventaId()).orElseThrow(() -> new DatabaseRuleException("Codigo de venta no existe"));
        Producto p = productoRepository.findById(dto.productoId()).orElseThrow(() -> new DatabaseRuleException("Codigo producto no existe"));
        DetalleVenta detalleVenta = detalleVentaMapper.DtoToEntity(dto, v, p);
        VentaDtoResponse ventaDtoResponse = ventaMapper.entityToDto(v);
        ProductoDtoResponseFactura productoDtoResponse = productoMapper.entityToDtoFactura(p);
        return detalleVentaMapper.entityToDto(
                detalleVentaRepository.save(detalleVenta), ventaDtoResponse, productoDtoResponse
        );
    }

    @Override
    public DetalleVentaDtoResponse actualizar(DetalleVentaDtoRequest dto, Long id) {
        DetalleVenta detalleVenta = detalleVentaRepository.findById(id).orElseThrow(() -> new DatabaseRuleException("No existe el codigo del detalle de venta"));
        Venta v = ventaRepository.findById(dto.ventaId()).orElseThrow(() -> new DatabaseRuleException("Codigo de venta no existe"));
        Producto p = productoRepository.findById(dto.productoId()).orElseThrow(() -> new DatabaseRuleException("Codigo producto no existe"));
        VentaDtoResponse ventaDtoResponse = ventaMapper.entityToDto(v);
        ProductoDtoResponseFactura productoDtoResponse = productoMapper.entityToDtoFactura(p);
        detalleVentaMapper.updateDetalleVentaFromDto(detalleVenta, dto, v, p);
        return detalleVentaMapper.entityToDto(
                detalleVentaRepository.save(detalleVenta), ventaDtoResponse, productoDtoResponse
        );
    }

    @Override
    public void eliminar(Long id) {
        DetalleVenta detalleVenta = detalleVentaRepository.findById(id).orElseThrow(() -> new DatabaseRuleException("No existe dicho detalle venta a eliminar"));
        detalleVentaRepository.delete(detalleVenta);
    }

    @Override
    public List<DetalleVentaDtoResponse> listar() {
        return detalleVentaRepository.findAll().stream().map(
                dv -> detalleVentaMapper.entityToDto(dv,
                        ventaMapper.entityToDto(dv.getVenta()),
                        productoMapper.entityToDtoFactura(dv.getProducto()))).toList();
    }

    @Override
    public List<DetalleVentaDtoResponse> buscarVentaId(Long id) {
        return detalleVentaRepository.findByVentaId(id).stream().map(
                dv -> detalleVentaMapper.entityToDto(dv,
                        ventaMapper.entityToDto(dv.getVenta()),
                        productoMapper.entityToDtoFactura(dv.getProducto()))).toList();
    }

    @Override
    public List<DetalleVentaDtoResponse> buscarProductoId(Long id) {
        return detalleVentaRepository.findByProductoId(id).stream().map(
                dv -> detalleVentaMapper.entityToDto(dv,
                        ventaMapper.entityToDto(dv.getVenta()),
                        productoMapper.entityToDtoFactura(dv.getProducto()))).toList();
    }

    @Override
    public List<DetalleVentaDtoResponse> filtrarProductoSubtotal(Long id, BigDecimal subtotal) {
        return detalleVentaRepository.findByProductoIdAndSubtotalGreaterThanEqual(id, subtotal).stream().map(
                dv -> detalleVentaMapper.entityToDto(dv,
                        ventaMapper.entityToDto(dv.getVenta()),
                        productoMapper.entityToDtoFactura(dv.getProducto()))).toList();
    }

    @Override
    public DetalleVentaDtoResponse buscarId(Long id) {
        DetalleVenta detalleVenta = detalleVentaRepository.findById(id).orElseThrow(() -> new DatabaseRuleException("No existe dicho id de detalle de venta!"));
        return detalleVentaMapper.entityToDto(detalleVenta, ventaMapper.entityToDto(detalleVenta.getVenta()),
                productoMapper.entityToDtoFactura(detalleVenta.getProducto()));
    }
}

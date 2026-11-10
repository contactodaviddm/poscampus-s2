package com.k4.poscampus.mapper;

import com.k4.poscampus.dto.request.DetalleVentaDtoRequest;
import com.k4.poscampus.dto.response.DetalleVentaDtoResponse;
import com.k4.poscampus.dto.response.ProductoDtoResponseFactura;
import com.k4.poscampus.dto.response.VentaDtoResponse;
import com.k4.poscampus.model.DetalleVenta;
import com.k4.poscampus.model.Producto;
import com.k4.poscampus.model.Venta;
import org.springframework.stereotype.Component;

@Component
public class DetalleVentaMapper {
    public DetalleVentaDtoResponse entityToDto(DetalleVenta detalle, VentaDtoResponse ventaDto, ProductoDtoResponseFactura productoDto){
        if(detalle==null || productoDto==null || ventaDto==null) return null;
        return new DetalleVentaDtoResponse(detalle.getId(), ventaDto, productoDto, detalle.getCantidad(), detalle.getSubtotal());
    }

    public DetalleVenta DtoToEntity(DetalleVentaDtoRequest dto, Venta venta, Producto producto){
        if(dto==null || venta==null || producto==null) return null;
        DetalleVenta detalle=new DetalleVenta();
        detalle.setCantidad(dto.cantidad());
        detalle.setSubtotal(dto.subtotal());
        detalle.setVenta(venta);
        detalle.setProducto(producto);
        return detalle;
    }
    public void updateDetalleVentaFromDto(DetalleVenta detalle, DetalleVentaDtoRequest dto, Venta venta, Producto producto){
        if(detalle==null || dto==null || venta==null || producto==null) return;
        detalle.setCantidad(dto.cantidad());
        detalle.setSubtotal(dto.subtotal());
        detalle.setVenta(venta);
        detalle.setProducto(producto);
    }
}

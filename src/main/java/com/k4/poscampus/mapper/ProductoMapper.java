package com.k4.poscampus.mapper;

import com.k4.poscampus.dto.request.ProductoDtoRequest;
import com.k4.poscampus.dto.response.ProductoDtoResponse;
import com.k4.poscampus.model.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {
    public ProductoDtoResponse entityToDto(Producto producto){
        if(producto==null) return null;
        return new ProductoDtoResponse(producto.getId(), producto.getNombre(), producto.getMarca(), producto.getStock(),
                producto.getPrecioCompra(), producto.getPrecioVenta());
    }

    public Producto DtoToEntity(ProductoDtoRequest dto){
        if(dto==null) return null;
        Producto p=new Producto();
        p.setNombre(dto.nombre());
        p.setMarca(dto.marca());
        p.setPrecioCompra(dto.precioCompra());
        p.setPrecioVenta(dto.precioVenta());
        p.setStock(dto.stock());
        return p;
    }

    public void updateEntityFromDto(Producto producto, ProductoDtoRequest dto){
        if(dto==null || producto==null) return;
        producto.setNombre(dto.nombre());
        producto.setMarca(dto.marca());
        producto.setPrecioCompra(dto.precioCompra());
        producto.setPrecioVenta(dto.precioVenta());
        producto.setStock(dto.stock());

    }
}

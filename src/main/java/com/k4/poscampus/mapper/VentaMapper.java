package com.k4.poscampus.mapper;

import com.k4.poscampus.dto.request.VentaDtoRequest;
import com.k4.poscampus.dto.response.VentaDtoResponse;
import com.k4.poscampus.model.Venta;
import org.springframework.stereotype.Component;

@Component
public class VentaMapper {
    public VentaDtoResponse entityToDto(Venta venta){
        if(venta==null) return null;
        return new VentaDtoResponse(venta.getId(), venta.getDocumentoCliente(), venta.getNombreCompleto(), venta.getTotal());
    }
    public Venta DtoToEntity(VentaDtoRequest dto){
        if(dto==null) return null;
        Venta venta=new Venta();
        venta.setDocumentoCliente(dto.documentoCliente());
        venta.setNombreCompleto(dto.nombreCompleto());
        venta.setTotal(dto.total());
        return venta;
    }
    public void updateEntityFromDto(Venta venta, VentaDtoRequest dto){
        if(venta==null || dto==null) return;
        venta.setDocumentoCliente(dto.documentoCliente());
        venta.setNombreCompleto(dto.nombreCompleto());
        venta.setTotal(dto.total());
    }
}

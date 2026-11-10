package com.k4.poscampus.repository;

import com.k4.poscampus.model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
    boolean existsByVentaId(Long id);
    List<DetalleVenta> findByVentaId(Long id);
    boolean existsByProductoId(Long id);
    List<DetalleVenta> findByProductoId(Long id);
    List<DetalleVenta> findByProductoIdAndSubtotalGreaterThanEqual(Long id, BigDecimal subtotal);
}

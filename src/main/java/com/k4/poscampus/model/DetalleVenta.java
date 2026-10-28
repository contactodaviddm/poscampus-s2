package com.k4.poscampus.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "detale_venta")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "venta_id")
    private Venta ventaId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "producto_id")
    private Producto productoId;
    @Column(nullable = false)
    private Integer cantidad;
    @Column(nullable = false)
    private BigDecimal subtotal;
}

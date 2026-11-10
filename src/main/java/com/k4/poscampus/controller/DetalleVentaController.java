package com.k4.poscampus.controller;

import com.k4.poscampus.dto.request.DetalleVentaDtoRequest;
import com.k4.poscampus.dto.response.DetalleVentaDtoResponse;
import com.k4.poscampus.service.DetalleVentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Validated
@Tag(name = "Modulo detalle de venta",
        description = "Este modulo administrara todo lo realacionado con...")
@RestController
@RequestMapping("/api/detalleventa")
@RequiredArgsConstructor
public class DetalleVentaController {
    private final DetalleVentaService detalleVentaService;
    @Operation(summary = "Permite agregar los detalles",
    description = "Obtiene todos los registros del sistema")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201",
                            description = "El registro se realizo correctamente"),
                    @ApiResponse(responseCode = "404",
                            description = "El registro se realizo correctamente")
            }
    )
    @PostMapping
    public ResponseEntity<DetalleVentaDtoResponse> guardar(@Valid @RequestBody DetalleVentaDtoRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(detalleVentaService.guardar(dto));
    }

    @GetMapping
    public ResponseEntity<List<DetalleVentaDtoResponse>> listar() {
        return ResponseEntity.ok(detalleVentaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleVentaDtoResponse> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(detalleVentaService.buscarId(id));
    }

    @GetMapping("/venta/{id}")
    public ResponseEntity<List<DetalleVentaDtoResponse>> buscarVenta(@PathVariable Long id) {
        return ResponseEntity.ok(detalleVentaService.buscarVentaId(id));
    }

    @GetMapping("/producto/{id}")
    public ResponseEntity<List<DetalleVentaDtoResponse>> buscarProducto(@PathVariable Long id) {
        return ResponseEntity.ok(detalleVentaService.buscarProductoId(id));
    }

    //http://localhost:8080/api/detalleventa/filtrarProductoSubtotal?id=1&subtotal=50000
    @GetMapping("/filtrarProductoSubtotal")
    public ResponseEntity<List<DetalleVentaDtoResponse>> buscarProductoSubtotal(@RequestParam Long id, @RequestParam BigDecimal subtotal) {
        return ResponseEntity.ok(detalleVentaService.filtrarProductoSubtotal(id, subtotal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        detalleVentaService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleVentaDtoResponse> actualizar(@RequestBody DetalleVentaDtoRequest dto, @PathVariable Long id) {
        return ResponseEntity.ok(detalleVentaService.actualizar(dto, id));
    }

}

package com.k4.poscampus.controller;

import com.k4.poscampus.dto.request.VentaDtoRequest;
import com.k4.poscampus.dto.response.VentaDtoResponse;
import com.k4.poscampus.service.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venta")
@RequiredArgsConstructor
public class VentaController {
    private final VentaService ventaService;

    @PostMapping
    public ResponseEntity<VentaDtoResponse> guardar(@RequestBody VentaDtoRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ventaService.guardar(dto));
    }

    @GetMapping
    public ResponseEntity<List<VentaDtoResponse>> listar() {
        return ResponseEntity.ok(ventaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDtoResponse> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(ventaService.buscarId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ventaService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaDtoResponse> actualizar(@RequestBody VentaDtoRequest dto, @PathVariable Long id) {
        return ResponseEntity.ok(ventaService.actualizar(dto, id));
    }

}

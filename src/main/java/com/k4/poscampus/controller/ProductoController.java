package com.k4.poscampus.controller;

import com.k4.poscampus.dto.request.ProductoDtoRequest;
import com.k4.poscampus.dto.response.ProductoDtoResponse;
import com.k4.poscampus.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
//http://localhost:8080/api/producto
@RequestMapping("/api/producto")
@RequiredArgsConstructor
public class ProductoController {
    private final ProductoService productoService;

    @PostMapping
    public ResponseEntity<ProductoDtoResponse> guardar(@Valid @RequestBody ProductoDtoRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.guardar(dto));
    }

    //http://localhost:8080/api/producto
    @GetMapping
    public ResponseEntity<List<ProductoDtoResponse>> listar() {
        return ResponseEntity.ok(productoService.listar());
    }

    //http://localhost:8080/api/producto/2
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDtoResponse> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.buscarId(id));
    }

    //http://localhost:8080/api/producto/filtroMarca?nombre=Samsung
    @GetMapping("/filtroMarca")
    public ResponseEntity<List<ProductoDtoResponse>> buscarMarca(@RequestParam String nombre) {
        return ResponseEntity.ok(productoService.buscarMarca(nombre));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDtoResponse> actualizar(@RequestBody ProductoDtoRequest dto, @PathVariable Long id) {
        return ResponseEntity.ok(productoService.actualizar(dto, id));
    }

}

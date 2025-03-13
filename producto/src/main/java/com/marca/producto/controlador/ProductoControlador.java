package com.marca.producto.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@Slf4j
public class ProductoControlador {
    
    private final ProductoServicio servicio;

    public ProductoControlador(ProductoServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listarTodos() {
        return ResponseEntity.ok(servicio.buscarTodos());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Producto> buscarPorCodigo(@PathVariable String codigo) {
        try {
            return ResponseEntity.ok(servicio.buscarPorCodigo(codigo));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody Producto producto) {
        return ResponseEntity.ok(servicio.crear(producto));
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Producto> actualizar(
            @PathVariable String codigo, 
            @RequestBody Producto producto) {
        try {
            producto.setCodProducto(codigo);
            return ResponseEntity.ok(servicio.actualizar(producto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> eliminar(@PathVariable String codigo) {
        servicio.eliminar(codigo);
        return ResponseEntity.ok().build();
    }
} 
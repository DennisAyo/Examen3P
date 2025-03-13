package com.marca.producto.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
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
    public ResponseEntity<ProductoDTO> buscarPorCodigo(@PathVariable String codigo) {
        try {
            return ResponseEntity.ok(servicio.buscarPorCodigo(codigo));
        } catch (RuntimeException e) {
            log.error("Error al buscar producto: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> crearProducto(@Valid @RequestBody ProductoDTO producto) {
        try {
            return ResponseEntity.ok(servicio.crearProducto(producto));
        } catch (Exception e) {
            log.error("Error al crear producto: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<ProductoDTO> modificarProducto(
            @PathVariable String codigo, 
            @Valid @RequestBody ProductoDTO producto) {
        try {
            producto.setCodProducto(codigo);
            return ResponseEntity.ok(servicio.modificarProducto(producto));
        } catch (RuntimeException e) {
            log.error("Error al modificar producto: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> eliminar(@PathVariable String codigo) {
        servicio.eliminar(codigo);
        return ResponseEntity.ok().build();
    }
} 
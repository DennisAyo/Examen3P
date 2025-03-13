package com.marca.facturacion.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/facturas")
@Slf4j
public class FacturacionControlador {
    
    private final FacturacionServicio servicio;

    public FacturacionControlador(FacturacionServicio servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public ResponseEntity<FacturaDTO> crearFactura(@Valid @RequestBody CrearFacturaDTO factura) {
        try {
            return ResponseEntity.ok(servicio.crearFactura(factura));
        } catch (Exception e) {
            log.error("Error al crear factura: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<FacturaDTO> obtenerPorCodigo(@PathVariable("codigo") Integer codigo) {
        try {
            Factura factura = servicio.buscarPorCodigo(codigo);
            return ResponseEntity.ok(mapper.toDTO(factura));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 
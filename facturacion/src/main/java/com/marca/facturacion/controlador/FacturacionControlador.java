package com.marca.facturacion.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/facturas")
@Slf4j
public class FacturacionControlador {
    
    private final FacturacionServicio servicio;
    private final FacturaMapper mapper;

    public FacturacionControlador(FacturacionServicio servicio, FacturaMapper mapper) {
        this.servicio = servicio;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<FacturaDTO> crear(@Valid @RequestBody FacturaDTO dto) {
        try {
            Factura factura = servicio.crearFactura(mapper.toModel(dto));
            return ResponseEntity.ok(mapper.toDTO(factura));
        } catch (Exception e) {
            log.error("Error al crear la factura", e);
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
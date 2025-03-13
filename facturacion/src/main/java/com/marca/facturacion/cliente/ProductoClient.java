package com.marca.facturacion.cliente;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "producto-service", url = "${producto.service.url}")
public interface ProductoClient {
    
    @GetMapping("/api/v1/productos/{codigo}")
    ResponseEntity<ProductoDTO> obtenerProducto(@PathVariable("codigo") String codigo);
} 
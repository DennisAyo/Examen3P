package com.marca.facturacion.cliente;

import com.marca.facturacion.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "producto-service", url = "${producto.service.url}")
public interface ProductoClient {
    
    @GetMapping("/api/productos/{codigo}")
    ProductoDTO obtenerProducto(@PathVariable("codigo") String codigo);
} 
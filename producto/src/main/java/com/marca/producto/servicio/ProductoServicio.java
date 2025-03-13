package com.marca.producto.servicio;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Service
@Slf4j
public class ProductoServicio {
    
    private final ProductoRepositorio repositorio;

    public ProductoServicio(ProductoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public List<Producto> buscarTodos() {
        return repositorio.findAll();
    }

    public Producto buscarPorCodigo(String codigo) {
        return repositorio.findById(codigo)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public Producto crear(Producto producto) {
        return repositorio.save(producto);
    }

    public Producto actualizar(Producto producto) {
        if (!repositorio.existsById(producto.getCodProducto())) {
            throw new RuntimeException("Producto no encontrado");
        }
        return repositorio.save(producto);
    }

    public void eliminar(String codigo) {
        repositorio.deleteById(codigo);
    }
} 
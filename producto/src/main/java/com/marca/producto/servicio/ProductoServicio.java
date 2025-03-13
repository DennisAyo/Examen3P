package com.marca.producto.servicio;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Service
@Slf4j
public class ProductoServicio {
    
    private final ProductoRepositorio repositorio;
    private final ProductoMapper mapper;

    public ProductoServicio(ProductoRepositorio repositorio, ProductoMapper mapper) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    public List<Producto> buscarTodos() {
        return repositorio.findAll();
    }

    public ProductoDTO buscarPorCodigo(String codigo) {
        Producto producto = repositorio.findById(codigo)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + codigo));
        return mapper.toDTO(producto);
    }

    public ProductoDTO crearProducto(ProductoDTO productoDTO) {
        Producto producto = mapper.toEntity(productoDTO);
        producto = repositorio.save(producto);
        return mapper.toDTO(producto);
    }

    public ProductoDTO modificarProducto(ProductoDTO productoDTO) {
        if (!repositorio.existsById(productoDTO.getCodProducto())) {
            throw new RuntimeException("Producto no encontrado: " + productoDTO.getCodProducto());
        }
        Producto producto = mapper.toEntity(productoDTO);
        producto = repositorio.save(producto);
        return mapper.toDTO(producto);
    }

    public void eliminar(String codigo) {
        repositorio.deleteById(codigo);
    }
} 
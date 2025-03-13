package com.marca.producto.repositorio;

import com.marca.producto.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, String> {
    Optional<Producto> findByCodProducto(String codProducto);
} 
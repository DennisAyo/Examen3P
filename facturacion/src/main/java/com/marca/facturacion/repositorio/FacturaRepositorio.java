package com.marca.facturacion.repositorio;

import com.marca.facturacion.modelo.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacturaRepositorio extends JpaRepository<Factura, Integer> {
    List<Factura> findByIdentificacion(String identificacion);
} 
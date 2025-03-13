package com.marca.facturacion.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacturaDetalleRepositorio extends JpaRepository<FacturaDetalle, Integer> {
    List<FacturaDetalle> findByFacturaCodFactura(Integer codFactura);
} 
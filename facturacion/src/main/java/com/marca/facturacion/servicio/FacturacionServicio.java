package com.marca.facturacion.servicio;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class FacturacionServicio {
    
    private final FacturaRepositorio facturaRepositorio;
    private final FacturaDetalleRepositorio detalleRepositorio;
    private final ProductoClient productoClient; // Cliente para comunicación con microservicio Producto

    public FacturacionServicio(FacturaRepositorio facturaRepositorio, 
                             FacturaDetalleRepositorio detalleRepositorio,
                             ProductoClient productoClient) {
        this.facturaRepositorio = facturaRepositorio;
        this.detalleRepositorio = detalleRepositorio;
        this.productoClient = productoClient;
    }

    @Transactional
    public Factura crearFactura(Factura factura) {
        factura.setFecha(LocalDateTime.now());
        calcularTotales(factura);
        return facturaRepositorio.save(factura);
    }

    private void calcularTotales(Factura factura) {
        BigDecimal subtotal = BigDecimal.ZERO;
        BigDecimal iva = BigDecimal.ZERO;
        BigDecimal total = BigDecimal.ZERO;

        for (FacturaDetalle detalle : factura.getDetalles()) {
            // Aquí iría la lógica para calcular los totales
            detalle.setFactura(factura);
        }

        factura.setSubtotal(subtotal);
        factura.setIva(iva);
        factura.setTotal(total);
    }

    public Factura buscarPorCodigo(Integer codigo) {
        return facturaRepositorio.findById(codigo)
            .orElseThrow(() -> new NotFoundException(codigo.toString(), "Factura"));
    }

    public List<Factura> buscarPorIdentificacion(String identificacion) {
        return facturaRepositorio.findByIdentificacion(identificacion);
    }
} 
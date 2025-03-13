package com.marca.facturacion.servicio;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Service
@Slf4j
public class FacturacionServicio {
    
    private final FacturaRepositorio facturaRepositorio;
    private final FacturaDetalleRepositorio detalleRepositorio;
    private final ProductoClient productoClient;

    public FacturacionServicio(FacturaRepositorio facturaRepositorio, 
                             FacturaDetalleRepositorio detalleRepositorio,
                             ProductoClient productoClient,
                             FacturaMapper mapper) {
        this.facturaRepositorio = facturaRepositorio;
        this.detalleRepositorio = detalleRepositorio;
        this.productoClient = productoClient;
        this.mapper = mapper;
    }

    @Transactional
    public FacturaDTO crearFactura(CrearFacturaDTO crearFacturaDTO) {
        
        Factura factura = new Factura();
        factura.setTipoIdentificacion(crearFacturaDTO.getCabecera().getTipoIdentificacion());
        factura.setIdentificacion(crearFacturaDTO.getCabecera().getIdentificacion());
        factura.setNombre(crearFacturaDTO.getCabecera().getNombre());
        factura.setFecha(LocalDateTime.now());

        List<FacturaDetalle> detalles = new ArrayList<>();
        BigDecimal subtotalFactura = BigDecimal.ZERO;
        BigDecimal ivaFactura = BigDecimal.ZERO;

        for (FacturaDetalleDTO detalleDTO : crearFacturaDTO.getDetalles()) {
            
            ProductoDTO producto = productoClient.obtenerProducto(detalleDTO.getCodProducto());
            
            FacturaDetalle detalle = new FacturaDetalle();
            detalle.setFactura(factura);
            detalle.setCodProducto(detalleDTO.getCodProducto());
            detalle.setCantidad(detalleDTO.getCantidad());
            detalle.setPrecioUnitario(producto.getPrecio());
            
            BigDecimal subtotal = producto.getPrecio().multiply(detalleDTO.getCantidad());
            BigDecimal iva = subtotal.multiply(new BigDecimal("0.12"));
            
            detalle.setSubtotal(subtotal);
            detalle.setIva(iva);
            detalle.setTotal(subtotal.add(iva));
            
            subtotalFactura = subtotalFactura.add(subtotal);
            ivaFactura = ivaFactura.add(iva);
            
            detalles.add(detalle);
        }

        factura.setSubtotal(subtotalFactura);
        factura.setIva(ivaFactura);
        factura.setTotal(subtotalFactura.add(ivaFactura));
        factura.setDetalles(detalles);

        factura = facturaRepositorio.save(factura);
        return mapper.toDTO(factura);
    }

    public Factura buscarPorCodigo(Integer codigo) {
        return facturaRepositorio.findById(codigo)
            .orElseThrow(() -> new NotFoundException(codigo.toString(), "Factura"));
    }

    public List<Factura> buscarPorIdentificacion(String identificacion) {
        return facturaRepositorio.findByIdentificacion(identificacion);
    }
} 
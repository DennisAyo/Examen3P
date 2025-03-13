package com.marca.facturacion.modelo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "FACTURA_DETALLE")
@Data
@NoArgsConstructor
public class FacturaDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_DETALLE")
    private Integer codDetalle;

    @ManyToOne
    @JoinColumn(name = "COD_FACTURA")
    private Factura factura;

    @Column(name = "COD_PRODUCTO", length = 32)
    private String codProducto;

    @Column(name = "CANTIDAD", precision = 5, scale = 0)
    private BigDecimal cantidad;

    @Column(name = "PRECIO_UNITARIO", precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @Column(name = "SUBTOTAL", precision = 10, scale = 2)
    private BigDecimal subtotal;

    @Column(name = "IVA", precision = 10, scale = 2)
    private BigDecimal iva;

    @Column(name = "TOTAL", precision = 10, scale = 2)
    private BigDecimal total;
} 
package com.marca.facturacion.modelo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "FACTURA")
@Data
@NoArgsConstructor
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_FACTURA")
    private Integer codFactura;

    @Column(name = "TIPO_IDENTIFICACION", length = 3)
    private String tipoIdentificacion;

    @Column(name = "IDENTIFICACION", length = 20)
    private String identificacion;

    @Column(name = "NOMBRE", length = 100)
    private String nombre;

    @Column(name = "FECHA")
    private LocalDateTime fecha;

    @Column(name = "SUBTOTAL", precision = 10, scale = 2)
    private BigDecimal subtotal;

    @Column(name = "IVA", precision = 10, scale = 2)
    private BigDecimal iva;

    @Column(name = "TOTAL", precision = 10, scale = 2)
    private BigDecimal total;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL)
    private List<FacturaDetalle> detalles;
}


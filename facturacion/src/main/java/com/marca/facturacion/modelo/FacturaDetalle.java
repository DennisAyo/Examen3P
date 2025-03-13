@Data
@NoArgsConstructor
@Entity
@Table(name = "FACTURA_DETALLE")
public class FacturaDetalle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_DETALLE")
    private Integer codDetalle;

    @ManyToOne
    @JoinColumn(name = "COD_FACTURA", nullable = false)
    private Factura factura;

    @NotNull
    @Column(name = "COD_PRODUCTO", length = 32, nullable = false)
    private String codProducto;

    @NotNull
    @Column(name = "CANTIDAD", precision = 5, scale = 0, nullable = false)
    private BigDecimal cantidad;

    @NotNull
    @Column(name = "PRECIO_UNITARIO", precision = 10, scale = 2, nullable = false)
    private BigDecimal precioUnitario;

    @NotNull
    @Column(name = "SUBTOTAL", precision = 10, scale = 2, nullable = false)
    private BigDecimal subtotal;

    @NotNull
    @Column(name = "IVA", precision = 10, scale = 2, nullable = false)
    private BigDecimal iva;

    @NotNull
    @Column(name = "TOTAL", precision = 10, scale = 2, nullable = false)
    private BigDecimal total;
} 
@Data
@NoArgsConstructor
@Entity
@Table(name = "FACTURA")
public class Factura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_FACTURA")
    private Integer codFactura;

    @NotNull
    @Column(name = "TIPO_IDENTIFICACION", length = 3, nullable = false)
    private String tipoIdentificacion;

    @NotNull
    @Column(name = "IDENTIFICACION", length = 20, nullable = false)
    private String identificacion;

    @NotNull
    @Column(name = "NOMBRE", length = 100, nullable = false)
    private String nombre;

    @NotNull
    @Column(name = "FECHA", nullable = false)
    private LocalDateTime fecha;

    @NotNull
    @Column(name = "SUBTOTAL", precision = 10, scale = 2, nullable = false)
    private BigDecimal subtotal;

    @NotNull
    @Column(name = "IVA", precision = 10, scale = 2, nullable = false)
    private BigDecimal iva;

    @NotNull
    @Column(name = "TOTAL", precision = 10, scale = 2, nullable = false)
    private BigDecimal total;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL)
    private List<FacturaDetalle> detalles;
} 
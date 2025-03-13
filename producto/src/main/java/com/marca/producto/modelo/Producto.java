@Entity
@Table(name = "PRODUCTO")
@Data
@NoArgsConstructor
public class Producto {
    @Id
    @Column(name = "COD_PRODUCTO", length = 32)
    private String codProducto;

    @Column(name = "NOMBRE", length = 64, nullable = false)
    private String nombre;

    @Column(name = "EXISTENCIA", precision = 5, scale = 0)
    private BigDecimal existencia;

    @Column(name = "PRECIO", precision = 10, scale = 2)
    private BigDecimal precio;
} 
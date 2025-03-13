@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacturaDTO {
    
    private Integer codFactura;

    @NotBlank(message = "El tipo de identificación es requerido")
    @Size(max = 3, message = "El tipo de identificación no debe exceder 3 caracteres")
    private String tipoIdentificacion;

    @NotBlank(message = "La identificación es requerida")
    @Size(max = 20, message = "La identificación no debe exceder 20 caracteres")
    private String identificacion;

    @NotBlank(message = "El nombre es requerido")
    @Size(max = 100, message = "El nombre no debe exceder 100 caracteres")
    private String nombre;

    private LocalDateTime fecha;
    private BigDecimal subtotal;
    private BigDecimal iva;
    private BigDecimal total;
    private List<FacturaDetalleDTO> detalles;
} 
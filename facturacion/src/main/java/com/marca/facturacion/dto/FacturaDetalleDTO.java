@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturaDetalleDTO {
    @NotBlank(message = "El código de producto es requerido")
    private String codProducto;
    
    @NotNull(message = "La cantidad es requerida")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private BigDecimal cantidad;
} 
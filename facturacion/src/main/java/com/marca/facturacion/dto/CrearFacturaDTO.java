@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrearFacturaDTO {
    @NotNull(message = "La cabecera es requerida")
    private FacturaCabeceraDTO cabecera;
    
    @NotEmpty(message = "Debe incluir al menos un detalle")
    private List<FacturaDetalleDTO> detalles;
} 
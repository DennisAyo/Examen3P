@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturaCabeceraDTO {
    @NotBlank(message = "El tipo de identificación es requerido")
    private String tipoIdentificacion;
    
    @NotBlank(message = "La identificación es requerida")
    private String identificacion;
    
    @NotBlank(message = "El nombre es requerido")
    private String nombre;
} 
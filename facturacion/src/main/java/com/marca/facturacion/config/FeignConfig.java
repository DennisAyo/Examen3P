@Configuration
public class FeignConfig {
    
    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }
}

class CustomErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 404) {
            return new NotFoundException("Producto no encontrado", "Producto");
        }
        return defaultErrorDecoder.decode(methodKey, response);
    }
} 
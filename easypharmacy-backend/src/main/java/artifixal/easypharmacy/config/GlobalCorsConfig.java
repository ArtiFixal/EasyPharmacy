package artifixal.easypharmacy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * Configures CORS policy globally.
 * 
 * @author ArtiFixal
 */
@Configuration
public class GlobalCorsConfig implements WebFluxConfigurer{
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("*");
    }
}

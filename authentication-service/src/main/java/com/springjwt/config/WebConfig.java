

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Gilt für alle Endpunkte
                .allowedOrigins("http://localhost:4200") // Angular-Frontend-URL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Erlaubte HTTP-Methoden
                .allowedHeaders("*") // Erlaubt alle Header
                .allowCredentials(true); // Für Cookies/Authentifizierung, falls benötigt
    }
}

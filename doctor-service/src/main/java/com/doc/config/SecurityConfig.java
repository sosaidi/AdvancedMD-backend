import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors() // Aktiviert die CORS-Konfiguration von WebMvcConfigurer
                .and()
                .csrf().disable() // Falls nötig, CSRF deaktivieren
                .authorizeHttpRequests()
                .requestMatchers("/api/**").permitAll(); // Beispiel: Alle /api/**-Routen erlauben
        return http.build();
    }
}

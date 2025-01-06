package com.doc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("Content-Type", "Authorization", "Accept", "X-Requested-With")
                .allowedOrigins("http://localhost:4200")  // Explicit origin instead of allowedOriginPatterns
                .allowCredentials(true);  // Allow credentials such as cookies or Authorization headers
    }
}

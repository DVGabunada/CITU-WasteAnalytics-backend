package com.citu.wasteanalyticsbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

/**
 * Global CORS filter — runs at the servlet filter level, before Spring MVC
 * dispatches the request. This correctly handles OPTIONS preflight requests
 * that would otherwise get a 403 from the DispatcherServlet.
 *
 * This replaces/supplements the WebMvcConfigurer approach which can fail
 * to handle preflights when deployed behind a reverse proxy (e.g. Render).
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // Allowed origins — add your Render frontend URL here
        config.setAllowedOrigins(List.of(
                "http://localhost:5173",                     // Vite dev server
                "http://localhost:3000",                     // fallback (CRA / other)
                "https://citu-wasteanalytics.onrender.com"  // Render – production frontend
        ));

        // Allow all standard HTTP methods including OPTIONS (preflight)
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));

        // Allow all headers
        config.setAllowedHeaders(List.of("*"));

        // Expose headers the browser is allowed to read
        config.setExposedHeaders(List.of("Authorization", "Content-Type"));

        // Cache preflight response for 1 hour (reduces preflight round-trips)
        config.setMaxAge(3600L);

        config.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", config);

        return new CorsFilter(source);
    }
}

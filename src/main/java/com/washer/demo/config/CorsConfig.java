package com.washer.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Clase de configuración para habilitar y personalizar las políticas de CORS (Cross-Origin Resource Sharing).
 * Esto permite que clientes alojados en dominios diferentes puedan acceder a los recursos expuestos por el backend.
 */
@Configuration
public class CorsConfig {

    /**
     * Define un bean que personaliza la configuración de CORS para la aplicación.
     *
     * @return una instancia de {@link WebMvcConfigurer} con reglas personalizadas para solicitudes CORS.
     *
     * Detalles de la configuración:
     * - Aplica las políticas de CORS a todas las rutas del servidor (`/**`).
     * - Permite solicitudes desde el origen específico "http://localhost:8080".
     * - Habilita los métodos HTTP: GET, POST, PUT, DELETE y OPTIONS.
     * - Acepta cualquier encabezado en las solicitudes.
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Aplica la configuración a todas las rutas del servidor
                        .allowedOrigins("http://localhost:8080") // Permite solicitudes desde este origen específico
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos HTTP permitidos
                        .allowedHeaders("*"); // Acepta cualquier encabezado HTTP
            }
        };
    }
}

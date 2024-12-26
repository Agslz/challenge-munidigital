package com.washer.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuración para OpenAPI (Swagger) que proporciona la documentación interactiva de la API.
 * Esta configuración define metadatos básicos y servidores disponibles para consumir la API.
 */
@Configuration
public class OpenApiConfig {

    /**
     * Configura una instancia personalizada de OpenAPI.
     *
     * @return una instancia de {@link OpenAPI} con los detalles de la API y los servidores configurados.
     *
     * Detalles:
     * - Título: API de Lavado de Autos.
     * - Versión: 1.0.
     * - Descripción: Documentación de la API para el sistema de lavado de autos.
     * - Servidores: incluye un servidor local en "http://localhost:8080" para desarrollo.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Lavado de Autos") // Título de la documentación
                        .version("1.0") // Versión de la API
                        .description("Documentación de la API para el sistema de lavado de autos")) // Descripción
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080") // URL del servidor local
                                .description("Servidor local de lavado de autos") // Descripción del servidor
                ));
    }
}

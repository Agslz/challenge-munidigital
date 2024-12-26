package com.washer.demo.config;

import com.washer.demo.filters.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuración de seguridad de la aplicación utilizando Spring Security.
 * Incluye la implementación de un filtro JWT, protección de endpoints y codificación de contraseñas.
 */
@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    /**
     * Constructor que inyecta la dependencia del filtro JWT.
     *
     * @param jwtFilter filtro personalizado para validar tokens JWT.
     */
    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    /**
     * Configura la cadena de filtros de seguridad.
     *
     * @param http instancia de {@link HttpSecurity} para personalizar la seguridad.
     * @return la configuración de la cadena de seguridad.
     * @throws Exception si ocurre un error durante la configuración.
     *
     * Detalles:
     * - Deshabilita CSRF para facilitar pruebas y desarrollo.
     * - Permite acceso público a endpoints relacionados con autenticación y documentación de API (Swagger y OpenAPI).
     * - Exige autenticación para cualquier otra solicitud.
     * - Añade un filtro JWT antes del filtro de autenticación por nombre de usuario y contraseña.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Deshabilitar CSRF para simplificar las pruebas
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/auth/**",       // Endpoints de autenticación
                                "/swagger-ui/**",     // Swagger UI
                                "/v3/api-docs/**",    // OpenAPI Docs
                                "/swagger-ui.html",   // Página principal de Swagger
                                "/api-docs/**"        // Swagger configuración
                        ).permitAll()
                        .anyRequest().authenticated() // Proteger todos los demás endpoints
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // Registrar el filtro JWT

        return http.build();
    }

    /**
     * Configura un codificador de contraseñas utilizando BCrypt.
     *
     * @return instancia de {@link BCryptPasswordEncoder}.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configura el manejador de autenticación compartido.
     *
     * @param http instancia de {@link HttpSecurity} para construir el manejador de autenticación.
     * @return una instancia de {@link AuthenticationManager}.
     * @throws Exception si ocurre un error durante la configuración.
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }
}

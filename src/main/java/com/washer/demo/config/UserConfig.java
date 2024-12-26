package com.washer.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Configuración de usuario en memoria para pruebas.
 * Esta configuración es útil durante el desarrollo para validar funcionalidades relacionadas con la autenticación.
 */
@Configuration
public class UserConfig {

    /**
     * Define un servicio de usuarios en memoria.
     *
     * @return una implementación de {@link UserDetailsService} que gestiona los detalles de usuario en memoria.
     *
     * Detalles:
     * - Crea un usuario con las siguientes credenciales:
     *   - Nombre de usuario: admin
     *   - Contraseña: password (encriptada con BCrypt).
     *   - Rol: USER.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        // Crear usuario en memoria con credenciales y rol
        UserDetails user = User.builder()
                .username("admin") // Nombre de usuario
                .password(new BCryptPasswordEncoder().encode("password")) // Contraseña encriptada con BCrypt
                .roles("USER") // Rol asignado al usuario
                .build();

        return new InMemoryUserDetailsManager(user); // Retorna un administrador de usuarios en memoria
    }
}

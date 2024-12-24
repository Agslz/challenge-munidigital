package com.washer.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class UserConfig {

    /**
     * Define un usuario en memoria para pruebas.
     *
     * @return UserDetailsService con los detalles del usuario.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        // Crear usuario en memoria
        UserDetails user = User.builder()
                .username("admin") // Nombre de usuario
                .password(new BCryptPasswordEncoder().encode("password")) // Contraseña encriptada
                .roles("USER") // Rol asignado
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}

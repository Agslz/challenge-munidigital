package com.washer.demo.controllers;

import com.washer.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Controlador REST que gestiona la autenticación de usuarios y la generación de tokens JWT.
 * Proporciona un endpoint para iniciar sesión y obtener un token de autenticación.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Endpoint POST para autenticar a un usuario y generar un token JWT.
     *
     * @param loginRequest Objeto que contiene las credenciales del usuario (username y password).
     * @return Un mapa con el token JWT generado si la autenticación es exitosa.
     * @throws RuntimeException en caso de credenciales inválidas.
     *
     * Flujo:
     * 1. Se autentica al usuario con el AuthenticationManager.
     * 2. Si las credenciales son válidas, se genera un token JWT asociado al username.
     * 3. El token se retorna como parte de la respuesta en formato JSON.
     */
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Autenticar al usuario
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            // Generar el token JWT
            String token = jwtUtil.generateToken(loginRequest.getUsername());

            // Respuesta con el token
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return response;

        } catch (AuthenticationException e) {
            throw new RuntimeException("Credenciales inválidas."); // Error en caso de autenticación fallida
        }
    }

    /**
     * Clase interna que representa la estructura del cuerpo de la solicitud de inicio de sesión.
     * Incluye los campos de username y password requeridos para la autenticación.
     */
    public static class LoginRequest {
        private String username;
        private String password;

        // Getters y Setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}

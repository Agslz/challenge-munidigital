package com.washer.demo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

/**
 * Utilidad para la generación y validación de tokens JWT.
 * Proporciona métodos para crear, firmar y validar tokens JWT en base a una clave secreta y un tiempo de expiración configurables.
 */
@Component
public class JwtUtil {

    // Clave secreta para firmar el token, obtenida desde el archivo de configuración.
    @Value("${jwt.secret}")
    private String secret;

    // Duración del token en milisegundos, obtenida desde el archivo de configuración.
    @Value("${jwt.expiration}")
    private long jwtExpirationMs;

    private Key signingKey;

    /**
     * Genera un token JWT con un usuario específico como sujeto.
     *
     * @param username Nombre del usuario para el cual se generará el token.
     * @return Token JWT generado.
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // Asigna el usuario como sujeto del token
                .setIssuedAt(new Date()) // Establece la fecha de emisión
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs)) // Define la expiración
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Firma el token con la clave y algoritmo
                .compact();
    }

    /**
     * Valida un token JWT comparándolo con el nombre de usuario esperado.
     *
     * @param token    Token JWT a validar.
     * @param username Nombre de usuario esperado.
     * @return true si el token es válido y corresponde al usuario; false en caso contrario.
     */
    public boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return extractedUsername.equals(username) && !isTokenExpired(token);
    }

    /**
     * Extrae el nombre de usuario (sujeto) del token.
     *
     * @param token Token JWT.
     * @return Nombre de usuario contenido en el token.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Verifica si un token JWT está expirado.
     *
     * @param token Token JWT a evaluar.
     * @return true si el token está expirado; false en caso contrario.
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Obtiene la fecha de expiración de un token JWT.
     *
     * @param token Token JWT.
     * @return Fecha de expiración del token.
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extrae un claim específico del token utilizando un resolver.
     *
     * @param <T>           Tipo de dato del claim.
     * @param token         Token JWT.
     * @param claimsResolver Función para resolver el claim.
     * @return Valor del claim resuelto.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extrae todos los claims presentes en un token JWT.
     *
     * @param token Token JWT.
     * @return Objeto Claims con todos los datos del token.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) // Establece la clave para la firma
                .build()
                .parseClaimsJws(token) // Analiza el token JWT
                .getBody();
    }

    /**
     * Obtiene la clave de firma utilizada para firmar o validar tokens.
     * Si la clave secreta es corta, se genera una automáticamente.
     *
     * @return Clave de firma.
     */
    private Key getSigningKey() {
        if (signingKey == null) {
            if (secret.length() < 32) {
                signingKey = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Genera una clave segura automáticamente
            } else {
                signingKey = Keys.hmacShaKeyFor(secret.getBytes()); // Utiliza la clave proporcionada
            }
        }
        return signingKey;
    }
}

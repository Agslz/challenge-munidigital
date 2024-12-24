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
 * Utilidad para generar y validar tokens JWT.
 */
@Component
public class JwtUtil {

    // La clave secreta para firmar el token, configurable desde application.properties
    @Value("${jwt.secret}")
    private String secret;

    // Duración del token en milisegundos, configurable desde application.properties
    @Value("${jwt.expiration}")
    private long jwtExpirationMs;

    private Key signingKey;

    /**
     * Genera un token JWT para el usuario dado.
     *
     * @param username Nombre de usuario (claim principal).
     * @return El token JWT generado.
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Valida el token y comprueba que corresponde al usuario proporcionado.
     *
     * @param token    Token JWT.
     * @param username Nombre de usuario esperado.
     * @return true si el token es válido; false en caso contrario.
     */
    public boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return extractedUsername.equals(username) && !isTokenExpired(token);
    }

    /**
     * Extrae el nombre de usuario (subject) del token.
     *
     * @param token Token JWT.
     * @return Nombre de usuario extraído del token.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Comprueba si el token está expirado.
     *
     * @param token Token JWT.
     * @return true si el token está expirado; false en caso contrario.
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extrae la fecha de expiración del token.
     *
     * @param token Token JWT.
     * @return Fecha de expiración del token.
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extrae un claim del token usando un resolver.
     *
     * @param <T>           Tipo del claim.
     * @param token         Token JWT.
     * @param claimsResolver Función para resolver el claim.
     * @return Claim resuelto.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extrae todos los claims del token.
     *
     * @param token Token JWT.
     * @return Claims extraídos del token.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Obtiene la clave de firma a partir de la clave secreta.
     *
     * @return Clave de firma.
     */
    private Key getSigningKey() {
        if (signingKey == null) {
            if (secret.length() < 32) {
                // Si la clave no es lo suficientemente larga, generar una segura automáticamente
                signingKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
            } else {
                signingKey = Keys.hmacShaKeyFor(secret.getBytes());
            }
        }
        return signingKey;
    }
}

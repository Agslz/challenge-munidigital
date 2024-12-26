package com.washer.demo.filters;

import com.washer.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filtro para validar el token JWT en cada solicitud HTTP.
 * Este filtro se ejecuta una vez por solicitud y valida el encabezado de autorización.
 * Si el token JWT es válido, se establece el contexto de seguridad para el usuario.
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Método que intercepta cada solicitud HTTP para validar el token JWT.
     *
     * @param request  Solicitud HTTP entrante.
     * @param response Respuesta HTTP saliente.
     * @param chain    Cadena de filtros para continuar con el procesamiento de la solicitud.
     * @throws ServletException si ocurre un error en el procesamiento del filtro.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // Ignorar rutas específicas como Swagger UI y OpenAPI Docs para evitar conflictos con autenticación
        String servletPath = request.getServletPath();
        if (servletPath.startsWith("/swagger-ui") || servletPath.startsWith("/v3/api-docs") || servletPath.startsWith("/swagger-ui.html")) {
            chain.doFilter(request, response);
            return; // Salir del filtro sin procesar el JWT
        }

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        // Extraer el token JWT del encabezado "Authorization" si está presente
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt); // Extraer el nombre de usuario del token
        }

        // Validar el token y configurar el contexto de seguridad si es válido
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (jwtUtil.validateToken(jwt, userDetails.getUsername())) {
                var authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken); // Establecer autenticación en el contexto
            }
        }

        // Continuar con el siguiente filtro en la cadena
        chain.doFilter(request, response);
    }
}

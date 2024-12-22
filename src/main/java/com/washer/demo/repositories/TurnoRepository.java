package com.washer.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.washer.demo.entities.Turno;

/**
 * Repositorio JPA para la entidad Turno.
 * Ofrece métodos CRUD automáticos y puede ser extendido para incluir consultas personalizadas.
 */
public interface TurnoRepository extends JpaRepository<Turno, Long> {
    // Métodos CRUD generados automáticamente por JpaRepository para la entidad Turno,
    // donde Long es el tipo de la clave primaria de Turno.
}

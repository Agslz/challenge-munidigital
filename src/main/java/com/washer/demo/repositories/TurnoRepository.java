package com.washer.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.washer.demo.entities.Turno;

/**
 * Repositorio JPA para gestionar la entidad Turno.
 * Proporciona operaciones CRUD (Crear, Leer, Actualizar, Eliminar) de manera automática
 * al extender JpaRepository.
 *
 * Funcionalidades principales:
 * - save(): Guarda o actualiza un turno en la base de datos.
 * - findById(): Busca un turno por su identificador único (ID).
 * - findAll(): Obtiene una lista de todos los turnos registrados.
 * - deleteById(): Elimina un turno utilizando su ID.
 *
 * Además, se pueden agregar métodos personalizados para consultas específicas si es necesario.
 */
public interface TurnoRepository extends JpaRepository<Turno, Long> {
    // Se pueden definir consultas personalizadas adicionales aquí si es necesario.
}

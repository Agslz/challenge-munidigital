package com.washer.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.washer.demo.entities.Cobro;

/**
 * Repositorio JPA para gestionar la entidad Cobro.
 * Proporciona acceso a las operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * de manera automática al extender JpaRepository.
 *
 * Funcionalidades principales:
 * - save(): Guarda o actualiza un cobro en la base de datos.
 * - findById(): Busca un cobro por su identificador único (ID).
 * - findAll(): Obtiene una lista de todos los cobros registrados.
 * - deleteById(): Elimina un cobro utilizando su ID.
 */
public interface CobroRepository extends JpaRepository<Cobro, Long> {
    // Se pueden definir consultas personalizadas adicionales si es necesario.
}

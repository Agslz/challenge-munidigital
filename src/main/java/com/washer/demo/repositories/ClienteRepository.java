package com.washer.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.washer.demo.entities.Cliente;

/**
 * Repositorio JPA para gestionar la entidad Cliente en la base de datos.
 * Este repositorio proporciona operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * de forma automática al extender JpaRepository.
 *
 * Métodos proporcionados automáticamente:
 * - save(): Guarda o actualiza un cliente en la base de datos.
 * - findById(): Busca un cliente por su identificador único (ID).
 * - findAll(): Obtiene una lista de todos los clientes registrados.
 * - deleteById(): Elimina un cliente utilizando su ID.
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Se pueden agregar métodos personalizados si es necesario, además de los proporcionados por JpaRepository.
}

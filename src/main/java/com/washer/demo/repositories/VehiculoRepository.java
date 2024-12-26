package com.washer.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.washer.demo.entities.Vehiculo;

/**
 * Repositorio JPA para gestionar la entidad Vehiculo.
 * Proporciona operaciones CRUD (Crear, Leer, Actualizar, Eliminar) de manera automática
 * al extender JpaRepository.
 *
 * Funcionalidades principales:
 * - save(): Guarda o actualiza un vehículo en la base de datos.
 * - findById(): Busca un vehículo por su identificador único (ID).
 * - findAll(): Obtiene una lista de todos los vehículos registrados.
 * - deleteById(): Elimina un vehículo utilizando su ID.
 *
 * Además, se pueden agregar métodos personalizados para consultas específicas si es necesario.
 */
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    // Se pueden definir consultas personalizadas adicionales aquí si es necesario.
}

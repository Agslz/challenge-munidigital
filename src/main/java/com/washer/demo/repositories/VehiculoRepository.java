package com.washer.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.washer.demo.entities.Vehiculo;

/**
 * Repositorio JPA para la entidad Vehiculo.
 * Este repositorio proporciona acceso a métodos CRUD automáticos para la entidad Vehiculo,
 * utilizando la herencia de JpaRepository.
 */
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    // JpaRepository<Vehiculo, Long> ofrece métodos CRUD básicos para la entidad Vehiculo,
    // donde Long es el tipo de la clave primaria de Vehiculo.
}

package com.washer.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.washer.demo.entities.Cobro;

/**
 * Repositorio JPA para la entidad Cobro.
 * Este repositorio permite acceder a métodos CRUD automáticos para la entidad Cobro,
 * aprovechando la herencia de JpaRepository.
 */
public interface CobroRepository extends JpaRepository<Cobro, Long> {
    // JpaRepository<Cobro, Long> proporciona métodos CRUD básicos para la entidad Cobro,
    // donde Long es el tipo de la clave primaria de Cobro.
}

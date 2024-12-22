package com.washer.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.washer.demo.entities.Cliente;

/**
 * Repositorio JPA para la entidad Cliente.
 * Este repositorio proporciona automáticamente métodos CRUD para la entidad Cliente
 * debido a la extensión de JpaRepository.
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Aquí, JpaRepository<Cliente, Long> define métodos CRUD para Cliente,
    // donde Long es el tipo de la clave primaria de Cliente.
}

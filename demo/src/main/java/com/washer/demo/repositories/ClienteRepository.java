package com.washer.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.washer.demo.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}


package com.washer.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.washer.demo.entities.Cobro;

public interface CobroRepository extends JpaRepository<Cobro, Long> {
}

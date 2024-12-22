package com.washer.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.washer.demo.entities.Turno;

public interface TurnoRepository extends JpaRepository<Turno, Long> {
}

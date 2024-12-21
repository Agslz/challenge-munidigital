package com.washer.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.washer.demo.entities.Vehiculo;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
}

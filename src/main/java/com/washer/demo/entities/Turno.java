package com.washer.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Date;

/**
 * Entidad que representa un turno en el sistema.
 * Esta clase se mapea a una tabla en la base de datos y maneja la información de los turnos programados para servicios.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Identificador único para el turno

    private Date fechaHora;  // Fecha y hora programadas para el turno
    private String estado;  // Estado actual del turno (p.ej., programado, completado, cancelado)
    private String tipoServicio;  // Tipo de servicio a realizarse durante el turno

    /**
     * Relación muchos a uno con la entidad Vehiculo.
     * Cada turno está asociado a un único vehículo.
     */
    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    @JsonBackReference  // Previene la serialización de JSON para evitar la recursión infinita
    private Vehiculo vehiculo;
}

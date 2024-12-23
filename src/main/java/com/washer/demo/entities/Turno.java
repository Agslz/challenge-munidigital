package com.washer.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    private Date fechaHora;  // Fecha y hora programadas para el turno (sin validaciones)

    @NotBlank(message = "El estado del turno no puede estar vacío.")
    @Pattern(regexp = "^(programado|completado|cancelado)$", message = "El estado debe ser: programado, completado o cancelado.")
    private String estado;  // Estado actual del turno

    @NotBlank(message = "El tipo de servicio no puede estar vacío.")
    @Size(max = 100, message = "El tipo de servicio no puede exceder los 100 caracteres.")
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

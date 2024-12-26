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
 * Entidad Turno que representa una cita programada para un servicio en el sistema.
 * Cada turno está asociado a un vehículo específico y contiene detalles como la fecha, estado y tipo de servicio.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Identificador único del turno, generado automáticamente.

    private Date fechaHora;  // Fecha y hora programadas para el turno.

    @NotBlank(message = "El estado del turno no puede estar vacío.")
    @Pattern(regexp = "^(programado|completado|cancelado)$", message = "El estado debe ser: programado, completado o cancelado.")
    private String estado;  // Estado actual del turno, restringido a valores predefinidos.

    @NotBlank(message = "El tipo de servicio no puede estar vacío.")
    @Size(max = 100, message = "El tipo de servicio no puede exceder los 100 caracteres.")
    private String tipoServicio;  // Descripción del tipo de servicio asignado al turno.

    /**
     * Relación muchos a uno con la entidad Vehiculo.
     * Cada turno pertenece a un único vehículo, y utiliza anotaciones de JSON para prevenir recursión infinita.
     */
    @ManyToOne
    @JoinColumn(name = "vehiculo_id") // Define la columna que almacena el ID del vehículo en la base de datos.
    @JsonBackReference // Manejo de referencia inversa para relaciones bidireccionales en JSON.
    private Vehiculo vehiculo;
}

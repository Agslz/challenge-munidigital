package com.washer.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Date;

/**
 * Entidad Cobro que representa un pago asociado a un turno en el sistema.
 * Incluye información sobre el monto, la fecha del cobro y su relación con la entidad Turno.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Cobro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Identificador único del cobro, generado automáticamente.

    @NotNull(message = "El monto no puede ser nulo.")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a 0.")
    private Double monto;  // Monto del cobro con validación para evitar valores nulos o negativos.

    private Date fecha;  // Fecha en la que se realizó el cobro.

    /**
     * Relación uno a uno con la entidad Turno.
     * Cada cobro está asociado a un turno específico en el sistema.
     * Utiliza anotaciones de JSON para prevenir problemas de serialización recursiva.
     */
    @OneToOne
    @JoinColumn(name = "turno_id") // Define la columna en la base de datos para la relación.
    @JsonBackReference // Manejo de referencia inversa para relaciones bidireccionales en JSON.
    @JsonIgnore // Evita incluir la información del turno en las respuestas JSON del cobro.
    private Turno turno;
}

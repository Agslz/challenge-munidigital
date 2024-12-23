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
 * Entidad que representa un cobro en el sistema.
 * Esta clase se mapea a una tabla en la base de datos y maneja la información de los cobros realizados.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Cobro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Identificador único para el cobro

    @NotNull(message = "El monto no puede ser nulo.")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a 0.")
    private Double monto;  // Monto del cobro

    @NotNull(message = "La fecha no puede ser nula.")
    @PastOrPresent(message = "La fecha debe ser en el pasado o el presente.")
    private Date fecha;  // Fecha en la que se realizó el cobro

    /**
     * Relación uno a uno con la entidad Turno.
     * El cobro está directamente relacionado con un turno específico.
     */
    @OneToOne
    @JoinColumn(name = "turno_id")
    @JsonBackReference  // Previene la serialización de JSON para evitar la recursión infinita
    @JsonIgnore  // Previene que la lista de turnos sea incluida en la serialización JSON
    private Turno turno;
}

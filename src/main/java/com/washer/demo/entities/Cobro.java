package com.washer.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
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

    private Double monto;  // Monto del cobro
    private Date fecha;  // Fecha en la que se realizó el cobro

    /**
     * Relación uno a uno con la entidad Turno.
     * El cobro está directamente relacionado con un turno específico.
     */
    @OneToOne
    @JoinColumn(name = "turno_id")
    @JsonBackReference  // Previene la serialización de JSON para evitar la recursión infinita
    private Turno turno;
}

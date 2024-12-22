package com.washer.demo.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Set;

/**
 * Entidad que representa un vehículo en el sistema.
 * Esta clase se mapea a una tabla en la base de datos y maneja la información de los vehículos.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Identificador único para el vehículo

    private String modelo;  // Modelo del vehículo
    private String matricula;  // Matrícula del vehículo
    private String tipo;  // Tipo de vehículo (p.ej., sedan, SUV)

    /**
     * Relación muchos a uno con la entidad Cliente.
     * Cada vehículo está asociado a un único cliente.
     */
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonBackReference  // Previene la serialización de JSON para evitar la recursión infinita
    private Cliente cliente;

    /**
     * Lista de turnos asociados al vehículo.
     * Es una relación de uno a muchos, con cascada en todas las operaciones y eliminación de huérfanos.
     */
    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference  // Administra la serialización de JSON para incluir turnos de manera adecuada
    private Set<Turno> turnos;
}
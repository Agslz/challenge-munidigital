package com.washer.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Set;

/**
 * Entidad Vehiculo que representa un vehículo registrado en el sistema.
 * Cada vehículo está asociado a un cliente específico y puede tener varios turnos programados.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Identificador único del vehículo, generado automáticamente.

    @NotBlank(message = "El modelo no puede estar vacío.")
    @Size(max = 50, message = "El modelo no puede exceder los 50 caracteres.")
    private String modelo;  // Modelo del vehículo con validación de obligatoriedad y longitud.

    @NotBlank(message = "La matrícula no puede estar vacía.")
    @Pattern(regexp = "^[A-Z0-9]{1,10}$", message = "La matrícula debe contener entre 1 y 10 caracteres alfanuméricos en mayúsculas.")
    private String matricula;  // Matrícula del vehículo con restricciones de formato y longitud.

    @NotBlank(message = "El tipo de vehículo no puede estar vacío.")
    @Size(max = 30, message = "El tipo de vehículo no puede exceder los 30 caracteres.")
    private String tipo;  // Tipo de vehículo, como sedan, SUV, etc.

    /**
     * Relación muchos a uno con la entidad Cliente.
     * Cada vehículo pertenece a un único cliente.
     * Se utiliza JsonBackReference para manejar relaciones bidireccionales en JSON.
     */
    @ManyToOne
    @JoinColumn(name = "cliente_id") // Define la columna que almacena el ID del cliente asociado.
    @JsonBackReference // Manejo de referencia inversa para evitar recursión infinita en JSON.
    @JsonIgnore // Excluye la información del cliente de la serialización JSON del vehículo.
    private Cliente cliente;

    /**
     * Relación uno a muchos con la entidad Turno.
     * Cada vehículo puede tener varios turnos asociados.
     * La relación incluye cascada para todas las operaciones y eliminación de huérfanos.
     */
    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Administra la serialización para incluir turnos correctamente.
    @JsonIgnore // Previene la serialización de la lista de turnos en las respuestas JSON.
    private Set<Turno> turnos;
}

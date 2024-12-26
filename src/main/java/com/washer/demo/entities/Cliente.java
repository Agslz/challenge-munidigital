package com.washer.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Set;

/**
 * Entidad Cliente que representa a un cliente en el sistema.
 * Incluye atributos básicos como nombre, correo electrónico, teléfono y una relación con vehículos asociados.
 * Se utiliza validación para garantizar la integridad de los datos.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único del cliente, generado automáticamente.

    @NotBlank(message = "El nombre no puede estar vacío.")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres.")
    private String nombre; // Nombre del cliente con validación de longitud y obligatoriedad.

    @NotBlank(message = "El correo electrónico no puede estar vacío.")
    @Email(message = "El correo electrónico debe tener un formato válido.")
    private String correoElectronico; // Correo electrónico del cliente con validación de formato.

    @NotBlank(message = "El teléfono no puede estar vacío.")
    @Pattern(regexp = "\\d{10}", message = "El teléfono debe contener exactamente 10 dígitos.")
    private String telefono; // Teléfono del cliente con validación de formato y longitud exacta.

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Manejo de referencia para relaciones bidireccionales en JSON.
    @JsonIgnore // Previene la serialización de la lista de vehículos en respuestas JSON.
    private Set<Vehiculo> vehiculos; // Conjunto de vehículos asociados al cliente.
}

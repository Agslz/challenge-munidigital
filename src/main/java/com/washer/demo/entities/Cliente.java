package com.washer.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.Set;

/**
 * Clase de entidad que representa un cliente en el sistema.
 * Esta clase se mapea a una tabla de base de datos y maneja la información del cliente.
 */
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Identificador único del cliente

    private String nombre;  // Nombre del cliente
    private String correoElectronico;  // Dirección de correo electrónico del cliente
    private String telefono;  // Número de teléfono del cliente

    /**
     * Lista de vehículos asociados con el cliente.
     * Es una relación de uno a muchos, gestionada desde el lado del cliente.
     * Todas las operaciones como persistir, remover y actualizar son cascadas a los vehículos.
     * La eliminación de huérfanos está habilitada para eliminar automáticamente vehículos desasociados del cliente.
     */
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference  // Maneja la serialización JSON para evitar recursión infinita
    @JsonIgnore  // Previene que la lista de vehículos sea incluida en la serialización JSON
    private Set<Vehiculo> vehiculos;

    /**
     * Constructor por defecto requerido por JPA.
     */
    public Cliente() {
    }

    /**
     * Construye un nuevo cliente con nombre, correo electrónico y número de teléfono.
     * @param nombre El nombre del cliente.
     * @param correoElectronico El correo electrónico del cliente.
     * @param telefono El número de teléfono del cliente.
     */
    public Cliente(String nombre, String correoElectronico, String telefono) {
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
    }

    // Getters y setters para id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getters y setters para nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getters y setters para correo electrónico
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    // Getters y setters para número de teléfono
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // Getters y setters para la lista de vehículos
    public Set<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(Set<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }
}

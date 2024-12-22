package com.washer.demo.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.Set;

/**
 * Entidad que representa un vehículo en el sistema.
 * Esta clase se mapea a una tabla en la base de datos y maneja la información de los vehículos.
 */
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

    /**
     * Constructor por defecto requerido por JPA.
     */
    public Vehiculo() {
    }

    /**
     * Construye un nuevo vehículo con modelo, matrícula, tipo y el cliente asociado.
     * @param modelo Modelo del vehículo.
     * @param matricula Matrícula del vehículo.
     * @param tipo Tipo de vehículo.
     * @param cliente Cliente asociado al vehículo.
     */
    public Vehiculo(String modelo, String matricula, String tipo, Cliente cliente) {
        this.modelo = modelo;
        this.matricula = matricula;
        this.tipo = tipo;
        this.cliente = cliente;
    }

    // Métodos de acceso para id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Métodos de acceso para modelo
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    // Métodos de acceso para matrícula
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    // Métodos de acceso para tipo
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // Métodos de acceso para cliente
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    // Métodos de acceso para turnos
    public Set<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(Set<Turno> turnos) {
        this.turnos = turnos;
    }
}

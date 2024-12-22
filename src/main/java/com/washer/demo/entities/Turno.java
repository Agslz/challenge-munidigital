package com.washer.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.Date;

/**
 * Entidad que representa un turno en el sistema.
 * Esta clase se mapea a una tabla en la base de datos y maneja la información de los turnos programados para servicios.
 */
@Entity
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Identificador único para el turno

    private Date fechaHora;  // Fecha y hora programadas para el turno
    private String estado;  // Estado actual del turno (p.ej., programado, completado, cancelado)
    private String tipoServicio;  // Tipo de servicio a realizarse durante el turno

    /**
     * Relación muchos a uno con la entidad Vehiculo.
     * Cada turno está asociado a un único vehículo.
     */
    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    @JsonBackReference  // Previene la serialización de JSON para evitar la recursión infinita
    private Vehiculo vehiculo;

    /**
     * Constructor por defecto requerido por JPA.
     */
    public Turno() {
    }

    /**
     * Construye un nuevo turno con fecha, hora, estado, tipo de servicio y el vehículo asociado.
     * @param fechaHora Fecha y hora del turno.
     * @param estado Estado del turno.
     * @param tipoServicio Tipo de servicio que se llevará a cabo.
     * @param vehiculo Vehículo asociado al turno.
     */
    public Turno(Date fechaHora, String estado, String tipoServicio, Vehiculo vehiculo) {
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.tipoServicio = tipoServicio;
        this.vehiculo = vehiculo;
    }

    // Métodos de acceso para id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Métodos de acceso para fecha y hora
    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    // Métodos de acceso para estado
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Métodos de acceso para tipo de servicio
    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    // Métodos de acceso para vehículo
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
}

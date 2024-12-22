package com.washer.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.Date;

/**
 * Entidad que representa un cobro en el sistema.
 * Esta clase se mapea a una tabla en la base de datos y maneja la información de los cobros realizados.
 */
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

    /**
     * Constructor por defecto requerido por JPA.
     */
    public Cobro() {
    }

    /**
     * Construye un nuevo cobro con monto, fecha y el turno asociado.
     * @param monto El monto del cobro.
     * @param fecha La fecha en que se efectuó el cobro.
     * @param turno El turno asociado a este cobro.
     */
    public Cobro(Double monto, Date fecha, Turno turno) {
        this.monto = monto;
        this.fecha = fecha;
        this.turno = turno;
    }

    // Métodos de acceso para id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Métodos de acceso para monto
    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    // Métodos de acceso para fecha
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    // Métodos de acceso para turno
    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }
}

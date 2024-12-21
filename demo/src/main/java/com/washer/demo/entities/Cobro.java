package com.washer.demo.entities;

import jakarta.persistence.*;
import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Cobro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double monto;
    private Date fecha;

    @OneToOne
    @JoinColumn(name = "turno_id")
    private Turno turno;
}

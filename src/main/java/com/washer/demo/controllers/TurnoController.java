package com.washer.demo.controllers;

import com.washer.demo.entities.Turno;
import com.washer.demo.services.TurnoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar operaciones relacionadas con la entidad Turno.
 * Proporciona endpoints para crear, leer, actualizar y eliminar turnos (operaciones CRUD),
 * así como actualizar el estado de un turno específico.
 */
@RestController
@RequestMapping("/api/turnos")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    /**
     * Endpoint para crear un nuevo turno.
     *
     * @param turno Objeto Turno validado que contiene los datos del turno a crear.
     * @return {@link ResponseEntity} con el turno creado y un código de estado 201 (CREATED).
     */
    @PostMapping
    public ResponseEntity<Turno> createTurno(@Valid @RequestBody Turno turno) {
        Turno savedTurno = turnoService.saveTurno(turno);
        return ResponseEntity.status(201).body(savedTurno);
    }

    /**
     * Endpoint para obtener un turno por su ID.
     *
     * @param id Identificador único del turno.
     * @return {@link ResponseEntity} con el turno encontrado o un estado 404 (NOT FOUND) si no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Turno> getTurnoById(@PathVariable Long id) {
        Turno turno = turnoService.getTurno(id);
        return ResponseEntity.ok(turno);
    }

    /**
     * Endpoint para obtener la lista de todos los turnos.
     *
     * @return {@link ResponseEntity} con una lista de todos los turnos registrados.
     */
    @GetMapping
    public ResponseEntity<List<Turno>> getAllTurnos() {
        List<Turno> turnos = turnoService.getAllTurnos();
        return ResponseEntity.ok(turnos);
    }

    /**
     * Endpoint para eliminar un turno por su ID.
     *
     * @param id Identificador único del turno a eliminar.
     * @return {@link ResponseEntity} con un código de estado 204 (NO CONTENT) si la operación es exitosa.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTurno(@PathVariable Long id) {
        turnoService.deleteTurno(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint para actualizar el estado de un turno específico.
     *
     * @param id Identificador único del turno.
     * @param estado Nuevo estado del turno.
     * @return {@link ResponseEntity} con un código de estado 204 (NO CONTENT) si la operación es exitosa.
     */
    @PutMapping("/{id}/estado")
    public ResponseEntity<Void> updateEstadoTurno(@PathVariable Long id, @RequestParam String estado) {
        turnoService.updateEstadoTurno(id, estado);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint para actualizar la información de un turno existente.
     *
     * @param id Identificador único del turno a actualizar.
     * @param turno Objeto Turno validado con los nuevos datos para actualizar.
     * @return {@link ResponseEntity} con el turno actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Turno> updateTurno(@PathVariable Long id, @Valid @RequestBody Turno turno) {
        Turno updatedTurno = turnoService.updateTurno(id, turno);
        return ResponseEntity.ok(updatedTurno);
    }
}

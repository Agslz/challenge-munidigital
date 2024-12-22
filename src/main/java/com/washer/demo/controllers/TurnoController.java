package com.washer.demo.controllers;

import com.washer.demo.entities.Turno;
import com.washer.demo.services.TurnoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de turnos.
 * Proporciona endpoints para operaciones CRUD sobre la entidad Turno.
 */
@RestController
@RequestMapping("/api/turnos")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    /**
     * Crea un nuevo turno.
     * @param turno Datos validados del turno a crear.
     * @return ResponseEntity con el turno creado.
     */
    @PostMapping
    public ResponseEntity<Turno> createTurno(@Valid @RequestBody Turno turno) {
        Turno savedTurno = turnoService.saveTurno(turno);
        return ResponseEntity.status(201).body(savedTurno);
    }

    /**
     * Obtiene un turno por su ID.
     * @param id ID del turno a buscar.
     * @return ResponseEntity con el turno encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Turno> getTurnoById(@PathVariable Long id) {
        Turno turno = turnoService.getTurno(id);
        return ResponseEntity.ok(turno);
    }

    /**
     * Obtiene una lista de todos los turnos.
     * @return ResponseEntity con la lista de turnos.
     */
    @GetMapping
    public ResponseEntity<List<Turno>> getAllTurnos() {
        List<Turno> turnos = turnoService.getAllTurnos();
        return ResponseEntity.ok(turnos);
    }

    /**
     * Elimina un turno por su ID.
     * @param id ID del turno a eliminar.
     * @return ResponseEntity con el estado de la operación.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTurno(@PathVariable Long id) {
        turnoService.deleteTurno(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Actualiza el estado de un turno.
     * @param id ID del turno a actualizar.
     * @param estado Nuevo estado del turno.
     * @return ResponseEntity con el estado de la operación.
     */
    @PutMapping("/{id}/estado")
    public ResponseEntity<Void> updateEstadoTurno(@PathVariable Long id, @RequestParam String estado) {
        turnoService.updateEstadoTurno(id, estado);
        return ResponseEntity.noContent().build();
    }

    /**
     * Actualiza la información de un turno existente.
     * @param id ID del turno a actualizar.
     * @param turno Datos validados del turno.
     * @return ResponseEntity con el turno actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Turno> updateTurno(@PathVariable Long id, @Valid @RequestBody Turno turno) {
        Turno updatedTurno = turnoService.updateTurno(id, turno);
        return ResponseEntity.ok(updatedTurno);
    }
}

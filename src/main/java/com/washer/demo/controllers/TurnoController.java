package com.washer.demo.controllers;

import com.washer.demo.entities.Turno;
import com.washer.demo.services.TurnoService;
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
     * @param turno Datos del turno a crear.
     * @return ResponseEntity con el turno creado o un estado de error si hay datos inválidos.
     */
    @PostMapping
    public ResponseEntity<Turno> createTurno(@RequestBody Turno turno) {
        try {
            Turno savedTurno = turnoService.saveTurno(turno);
            return ResponseEntity.ok(savedTurno);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Obtiene un turno por su ID.
     * @param id ID del turno a buscar.
     * @return ResponseEntity con el turno encontrado o un estado de no encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Turno> getTurnoById(@PathVariable Long id) {
        try {
            Turno turno = turnoService.getTurno(id);
            return ResponseEntity.ok(turno);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Obtiene una lista de todos los turnos.
     * @return Lista de turnos.
     */
    @GetMapping
    public List<Turno> getAllTurnos() {
        return turnoService.getAllTurnos();
    }

    /**
     * Elimina un turno por su ID.
     * @param id ID del turno a eliminar.
     * @return ResponseEntity con el estado de la operación.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTurno(@PathVariable Long id) {
        try {
            turnoService.deleteTurno(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Actualiza el estado de un turno.
     * @param id ID del turno a actualizar.
     * @param estado Nuevo estado del turno.
     * @return ResponseEntity con el estado de la operación.
     */
    @PutMapping("/{id}/estado")
    public ResponseEntity<Void> updateEstadoTurno(@PathVariable Long id, @RequestParam String estado) {
        try {
            turnoService.updateEstadoTurno(id, estado);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Actualiza la información de un turno existente.
     * @param id ID del turno a actualizar.
     * @param turno Datos actualizados del turno.
     * @return ResponseEntity con el turno actualizado o un estado de no encontrado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Turno> updateTurno(@PathVariable Long id, @RequestBody Turno turno) {
        try {
            Turno updatedTurno = turnoService.updateTurno(id, turno);
            return ResponseEntity.ok(updatedTurno);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

}

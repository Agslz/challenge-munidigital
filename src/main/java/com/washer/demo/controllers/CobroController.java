package com.washer.demo.controllers;

import com.washer.demo.entities.Cobro;
import com.washer.demo.entities.Turno;
import com.washer.demo.services.CobroService;
import com.washer.demo.services.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de cobros.
 * Proporciona endpoints para operaciones CRUD sobre la entidad Cobro y para actualizar el estado de Turno.
 */
@RestController
@RequestMapping("/api/cobros")
public class CobroController {

    @Autowired
    private CobroService cobroService;

    @Autowired
    private TurnoService turnoService;

    /**
     * Crea un nuevo cobro.
     * @param cobro Datos del cobro a crear.
     * @return ResponseEntity con el cobro creado o un estado de error si hay datos inválidos.
     */
    @PostMapping
    public ResponseEntity<Cobro> createCobro(@RequestBody Cobro cobro) {
        try {
            Cobro savedCobro = cobroService.saveCobro(cobro);
            return ResponseEntity.ok(savedCobro);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Obtiene un cobro por su ID.
     * @param id ID del cobro a buscar.
     * @return ResponseEntity con el cobro encontrado o un estado de no encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cobro> getCobroById(@PathVariable Long id) {
        try {
            Cobro cobro = cobroService.getCobro(id);
            return ResponseEntity.ok(cobro);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Actualiza el estado de un turno asociado a un cobro.
     * @param id ID del turno a actualizar.
     * @param estado Nuevo estado del turno.
     * @return ResponseEntity con el estado de la operación.
     */
    @PutMapping("/{id}/estado")
    public ResponseEntity<Void> updateEstadoTurno(@PathVariable Long id, @RequestParam String estado) {
        try {
            Turno turno = turnoService.getTurno(id);
            turno.setEstado(estado);
            turnoService.saveTurno(turno);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Obtiene una lista de todos los cobros.
     * @return Lista de cobros.
     */
    @GetMapping
    public List<Cobro> getAllCobros() {
        return cobroService.getAllCobros();
    }

    /**
     * Elimina un cobro por su ID.
     * @param id ID del cobro a eliminar.
     * @return ResponseEntity con el estado de la operación.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCobro(@PathVariable Long id) {
        try {
            cobroService.deleteCobro(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Actualiza un cobro existente.
     * @param id ID del cobro a actualizar.
     * @param cobro Datos actualizados del cobro.
     * @return ResponseEntity con el cobro actualizado o un estado de no encontrado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Cobro> updateCobro(@PathVariable Long id, @RequestBody Cobro cobro) {
        try {
            Cobro updatedCobro = cobroService.updateCobro(id, cobro);
            return ResponseEntity.ok(updatedCobro);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

}

package com.washer.demo.controllers;

import com.washer.demo.entities.Cobro;
import com.washer.demo.services.CobroService;
import com.washer.demo.services.TurnoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar operaciones relacionadas con la entidad Cobro.
 * Proporciona endpoints para crear, leer, actualizar y eliminar cobros, así como actualizar el estado de turnos asociados.
 */
@RestController
@RequestMapping("/api/cobros")
public class CobroController {

    @Autowired
    private CobroService cobroService;

    @Autowired
    private TurnoService turnoService;

    /**
     * Endpoint para crear un nuevo cobro.
     *
     * @param cobro Objeto Cobro validado que contiene los datos del cobro a crear.
     * @return {@link ResponseEntity} con el cobro creado y un código de estado 201 (CREATED).
     */
    @PostMapping
    public ResponseEntity<Cobro> createCobro(@Valid @RequestBody Cobro cobro) {
        Cobro savedCobro = cobroService.saveCobro(cobro);
        return ResponseEntity.status(201).body(savedCobro);
    }

    /**
     * Endpoint para obtener un cobro por su ID.
     *
     * @param id Identificador único del cobro.
     * @return {@link ResponseEntity} con el cobro encontrado o un estado 404 (NOT FOUND) si no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cobro> getCobroById(@PathVariable Long id) {
        Cobro cobro = cobroService.getCobro(id);
        return ResponseEntity.ok(cobro);
    }

    /**
     * Endpoint para actualizar el estado de un turno asociado a un cobro.
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
     * Endpoint para obtener la lista de todos los cobros.
     *
     * @return {@link ResponseEntity} con una lista de todos los cobros registrados.
     */
    @GetMapping
    public ResponseEntity<List<Cobro>> getAllCobros() {
        List<Cobro> cobros = cobroService.getAllCobros();
        return ResponseEntity.ok(cobros);
    }

    /**
     * Endpoint para eliminar un cobro por su ID.
     *
     * @param id Identificador único del cobro a eliminar.
     * @return {@link ResponseEntity} con un código de estado 204 (NO CONTENT) si la operación es exitosa.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCobro(@PathVariable Long id) {
        cobroService.deleteCobro(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint para actualizar un cobro existente.
     *
     * @param id Identificador único del cobro a actualizar.
     * @param cobro Objeto Cobro validado con los nuevos datos para actualizar.
     * @return {@link ResponseEntity} con el cobro actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Cobro> updateCobro(@PathVariable Long id, @Valid @RequestBody Cobro cobro) {
        Cobro updatedCobro = cobroService.updateCobro(id, cobro);
        return ResponseEntity.ok(updatedCobro);
    }
}

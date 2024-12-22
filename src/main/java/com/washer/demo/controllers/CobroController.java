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
     * @param cobro Datos validados del cobro a crear.
     * @return ResponseEntity con el cobro creado.
     */
    @PostMapping
    public ResponseEntity<Cobro> createCobro(@Valid @RequestBody Cobro cobro) {
        Cobro savedCobro = cobroService.saveCobro(cobro);
        return ResponseEntity.status(201).body(savedCobro);
    }

    /**
     * Obtiene un cobro por su ID.
     * @param id ID del cobro a buscar.
     * @return ResponseEntity con el cobro encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cobro> getCobroById(@PathVariable Long id) {
        Cobro cobro = cobroService.getCobro(id);
        return ResponseEntity.ok(cobro);
    }

    /**
     * Actualiza el estado de un turno asociado a un cobro.
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
     * Obtiene una lista de todos los cobros.
     * @return ResponseEntity con la lista de cobros.
     */
    @GetMapping
    public ResponseEntity<List<Cobro>> getAllCobros() {
        List<Cobro> cobros = cobroService.getAllCobros();
        return ResponseEntity.ok(cobros);
    }

    /**
     * Elimina un cobro por su ID.
     * @param id ID del cobro a eliminar.
     * @return ResponseEntity con el estado de la operación.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCobro(@PathVariable Long id) {
        cobroService.deleteCobro(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Actualiza un cobro existente.
     * @param id ID del cobro a actualizar.
     * @param cobro Datos validados del cobro.
     * @return ResponseEntity con el cobro actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Cobro> updateCobro(@PathVariable Long id, @Valid @RequestBody Cobro cobro) {
        Cobro updatedCobro = cobroService.updateCobro(id, cobro);
        return ResponseEntity.ok(updatedCobro);
    }
}

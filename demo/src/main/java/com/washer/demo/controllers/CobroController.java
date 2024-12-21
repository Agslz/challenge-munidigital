package com.washer.demo.controllers;

import com.washer.demo.entities.Cobro;
import com.washer.demo.entities.Turno;
import com.washer.demo.services.CobroService;
import com.washer.demo.services.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cobros")
public class CobroController {

    @Autowired
    private CobroService cobroService;

    @Autowired
    private TurnoService turnoService;

    @PostMapping
    public ResponseEntity<Cobro> createCobro(@RequestBody Cobro cobro) {
        try {
            Cobro savedCobro = cobroService.saveCobro(cobro);
            return ResponseEntity.ok(savedCobro);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cobro> getCobroById(@PathVariable Long id) {
        try {
            Cobro cobro = cobroService.getCobro(id);
            return ResponseEntity.ok(cobro);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

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


    @GetMapping
    public List<Cobro> getAllCobros() {
        return cobroService.getAllCobros();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCobro(@PathVariable Long id) {
        try {
            cobroService.deleteCobro(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

package com.washer.demo.controllers;

import com.washer.demo.entities.Cobro;
import com.washer.demo.services.CobroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cobros")
public class CobroController {
    @Autowired
    private CobroService cobroService;

    @PostMapping
    public ResponseEntity<Cobro> createCobro(@RequestBody Cobro cobro) {
        return ResponseEntity.ok(cobroService.saveCobro(cobro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cobro> getCobroById(@PathVariable Long id) {
        return cobroService.getCobro(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Cobro> getAllCobros() {
        return cobroService.getAllCobros();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCobro(@PathVariable Long id) {
        cobroService.deleteCobro(id);
        return ResponseEntity.ok().build();
    }
}

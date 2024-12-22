package com.washer.demo.controllers;

import com.washer.demo.entities.Vehiculo;
import com.washer.demo.services.VehiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de vehículos.
 * Proporciona endpoints para operaciones CRUD sobre la entidad Vehiculo.
 */
@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    /**
     * Crea un nuevo vehículo y lo asocia a un cliente existente.
     * @param vehiculo Datos validados del vehículo a crear.
     * @param clienteId ID del cliente al que se asociará el vehículo.
     * @return ResponseEntity con el vehículo creado.
     */
    @PostMapping
    public ResponseEntity<Vehiculo> createVehiculo(@Valid @RequestBody Vehiculo vehiculo, @RequestParam Long clienteId) {
        Vehiculo savedVehiculo = vehiculoService.saveVehiculo(vehiculo, clienteId);
        return ResponseEntity.status(201).body(savedVehiculo);
    }

    /**
     * Obtiene un vehículo por su ID.
     * @param id ID del vehículo a buscar.
     * @return ResponseEntity con el vehículo encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> getVehiculoById(@PathVariable Long id) {
        Vehiculo vehiculo = vehiculoService.getVehiculo(id);
        return ResponseEntity.ok(vehiculo);
    }

    /**
     * Obtiene una lista de todos los vehículos registrados.
     * @return ResponseEntity con la lista de vehículos.
     */
    @GetMapping
    public ResponseEntity<List<Vehiculo>> getAllVehiculos() {
        List<Vehiculo> vehiculos = vehiculoService.getAllVehiculos();
        return ResponseEntity.ok(vehiculos);
    }

    /**
     * Elimina un vehículo por su ID.
     * @param id ID del vehículo a eliminar.
     * @return ResponseEntity con el estado de la operación.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehiculo(@PathVariable Long id) {
        vehiculoService.deleteVehiculo(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Actualiza la información de un vehículo existente.
     * @param id ID del vehículo a actualizar.
     * @param vehiculo Datos validados del vehículo.
     * @return ResponseEntity con el vehículo actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Vehiculo> updateVehiculo(@PathVariable Long id, @Valid @RequestBody Vehiculo vehiculo) {
        Vehiculo updatedVehiculo = vehiculoService.updateVehiculo(id, vehiculo);
        return ResponseEntity.ok(updatedVehiculo);
    }
}

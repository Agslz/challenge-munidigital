package com.washer.demo.controllers;

import com.washer.demo.entities.Vehiculo;
import com.washer.demo.services.VehiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar operaciones relacionadas con la entidad Vehiculo.
 * Proporciona endpoints para crear, leer, actualizar y eliminar vehículos (operaciones CRUD).
 * Incluye la capacidad de asociar vehículos a clientes existentes.
 */
@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    /**
     * Endpoint para crear un nuevo vehículo y asociarlo a un cliente existente.
     *
     * @param vehiculo Objeto Vehiculo validado que contiene los datos del vehículo a crear.
     * @param clienteId Identificador único del cliente al que se asociará el vehículo.
     * @return {@link ResponseEntity} con el vehículo creado y un código de estado 201 (CREATED).
     */
    @PostMapping
    public ResponseEntity<Vehiculo> createVehiculo(@Valid @RequestBody Vehiculo vehiculo, @RequestParam Long clienteId) {
        Vehiculo savedVehiculo = vehiculoService.saveVehiculo(vehiculo, clienteId);
        return ResponseEntity.status(201).body(savedVehiculo);
    }

    /**
     * Endpoint para obtener un vehículo por su ID.
     *
     * @param id Identificador único del vehículo.
     * @return {@link ResponseEntity} con el vehículo encontrado o un estado 404 (NOT FOUND) si no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> getVehiculoById(@PathVariable Long id) {
        Vehiculo vehiculo = vehiculoService.getVehiculo(id);
        return ResponseEntity.ok(vehiculo);
    }

    /**
     * Endpoint para obtener la lista de todos los vehículos registrados.
     *
     * @return {@link ResponseEntity} con una lista de todos los vehículos registrados.
     */
    @GetMapping
    public ResponseEntity<List<Vehiculo>> getAllVehiculos() {
        List<Vehiculo> vehiculos = vehiculoService.getAllVehiculos();
        return ResponseEntity.ok(vehiculos);
    }

    /**
     * Endpoint para eliminar un vehículo por su ID.
     *
     * @param id Identificador único del vehículo a eliminar.
     * @return {@link ResponseEntity} con un código de estado 204 (NO CONTENT) si la operación es exitosa.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehiculo(@PathVariable Long id) {
        vehiculoService.deleteVehiculo(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint para actualizar la información de un vehículo existente.
     *
     * @param id Identificador único del vehículo a actualizar.
     * @param vehiculo Objeto Vehiculo validado con los nuevos datos para actualizar.
     * @return {@link ResponseEntity} con el vehículo actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Vehiculo> updateVehiculo(@PathVariable Long id, @Valid @RequestBody Vehiculo vehiculo) {
        Vehiculo updatedVehiculo = vehiculoService.updateVehiculo(id, vehiculo);
        return ResponseEntity.ok(updatedVehiculo);
    }
}

package com.washer.demo.controllers;

import com.washer.demo.entities.Vehiculo;
import com.washer.demo.services.VehiculoService;
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
     * @param vehiculo Datos del vehículo a crear.
     * @param clienteId ID del cliente al que se asociará el vehículo.
     * @return ResponseEntity con el vehículo creado o un estado de error si hay datos inválidos.
     */
    @PostMapping
    public ResponseEntity<Vehiculo> createVehiculo(@RequestBody Vehiculo vehiculo, @RequestParam Long clienteId) {
        try {
            Vehiculo savedVehiculo = vehiculoService.saveVehiculo(vehiculo, clienteId);
            return ResponseEntity.ok(savedVehiculo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Obtiene un vehículo por su ID.
     * @param id ID del vehículo a buscar.
     * @return ResponseEntity con el vehículo encontrado o un estado de no encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> getVehiculoById(@PathVariable Long id) {
        try {
            Vehiculo vehiculo = vehiculoService.getVehiculo(id);
            return ResponseEntity.ok(vehiculo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Obtiene una lista de todos los vehículos registrados.
     * @return Lista de vehículos.
     */
    @GetMapping
    public List<Vehiculo> getAllVehiculos() {
        return vehiculoService.getAllVehiculos();
    }

    /**
     * Elimina un vehículo por su ID.
     * @param id ID del vehículo a eliminar.
     * @return ResponseEntity con el estado de la operación.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehiculo(@PathVariable Long id) {
        try {
            vehiculoService.deleteVehiculo(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Actualiza la información de un vehículo existente.
     * @param id ID del vehículo a actualizar.
     * @param vehiculo Datos actualizados del vehículo.
     * @return ResponseEntity con el vehículo actualizado o un estado de no encontrado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Vehiculo> updateVehiculo(@PathVariable Long id, @RequestBody Vehiculo vehiculo) {
        try {
            Vehiculo updatedVehiculo = vehiculoService.updateVehiculo(id, vehiculo);
            return ResponseEntity.ok(updatedVehiculo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

}

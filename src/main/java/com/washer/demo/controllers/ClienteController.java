package com.washer.demo.controllers;

import com.washer.demo.entities.Cliente;
import com.washer.demo.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar operaciones relacionadas con la entidad Cliente.
 * Proporciona endpoints para crear, leer, actualizar y eliminar clientes (operaciones CRUD).
 */
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    /**
     * Endpoint para crear un nuevo cliente.
     *
     * @param cliente Objeto Cliente validado que contiene los datos del cliente a crear.
     * @return {@link ResponseEntity} con el cliente creado y un código de estado 201 (CREATED).
     */
    @PostMapping
    public ResponseEntity<Cliente> createCliente(@Valid @RequestBody Cliente cliente) {
        Cliente savedCliente = clienteService.saveCliente(cliente);
        return ResponseEntity.status(201).body(savedCliente);
    }

    /**
     * Endpoint para obtener un cliente por su ID.
     *
     * @param id Identificador único del cliente.
     * @return {@link ResponseEntity} con el cliente encontrado o un estado 404 (NOT FOUND) si no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Cliente cliente = clienteService.getCliente(id);
        return ResponseEntity.ok(cliente);
    }

    /**
     * Endpoint para obtener la lista de todos los clientes.
     *
     * @return {@link ResponseEntity} con una lista de todos los clientes.
     */
    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        List<Cliente> clientes = clienteService.getAllClientes();
        return ResponseEntity.ok(clientes);
    }

    /**
     * Endpoint para eliminar un cliente por su ID.
     *
     * @param id Identificador único del cliente a eliminar.
     * @return {@link ResponseEntity} con un código de estado 204 (NO CONTENT) si la operación es exitosa.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint para actualizar un cliente existente.
     *
     * @param id Identificador único del cliente a actualizar.
     * @param cliente Objeto Cliente validado con los nuevos datos para actualizar.
     * @return {@link ResponseEntity} con el cliente actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
        Cliente updatedCliente = clienteService.updateCliente(id, cliente);
        return ResponseEntity.ok(updatedCliente);
    }
}

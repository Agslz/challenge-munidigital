package com.washer.demo.controllers;

import com.washer.demo.entities.Cliente;
import com.washer.demo.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de clientes.
 * Proporciona endpoints para operaciones CRUD sobre la entidad Cliente.
 */
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    /**
     * Crea un nuevo cliente.
     * @param cliente Datos validados del cliente a crear.
     * @return ResponseEntity con el cliente creado.
     */
    @PostMapping
    public ResponseEntity<Cliente> createCliente(@Valid @RequestBody Cliente cliente) {
        Cliente savedCliente = clienteService.saveCliente(cliente);
        return ResponseEntity.status(201).body(savedCliente);
    }

    /**
     * Obtiene un cliente por su ID.
     * @param id ID del cliente a buscar.
     * @return ResponseEntity con el cliente encontrado o un estado de no encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Cliente cliente = clienteService.getCliente(id);
        return ResponseEntity.ok(cliente);
    }

    /**
     * Obtiene una lista de todos los clientes.
     * @return Lista de clientes.
     */
    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        List<Cliente> clientes = clienteService.getAllClientes();
        return ResponseEntity.ok(clientes);
    }

    /**
     * Elimina un cliente por su ID.
     * @param id ID del cliente a eliminar.
     * @return ResponseEntity con el estado de la operación.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Actualiza un cliente existente.
     * @param id ID del cliente a actualizar.
     * @param cliente Datos validados del cliente.
     * @return ResponseEntity con el cliente actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
        Cliente updatedCliente = clienteService.updateCliente(id, cliente);
        return ResponseEntity.ok(updatedCliente);
    }
}

package com.washer.demo.services;

import com.washer.demo.entities.Cliente;
import com.washer.demo.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Servicio para gestionar operaciones CRUD en la entidad Cliente.
 * Este servicio encapsula la lógica de negocio para el manejo de clientes.
 */
@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    /**
     * Guarda un cliente en la base de datos.
     * @param cliente El cliente a guardar.
     * @return El cliente guardado con su ID asignado.
     */
    public Cliente saveCliente(Cliente cliente) {
        // Validación de negocio adicional si es necesario
        return clienteRepository.save(cliente);
    }

    /**
     * Recupera un cliente por su ID.
     * @param id El ID del cliente a buscar.
     * @return El cliente encontrado.
     * @throws IllegalArgumentException Si no se encuentra un cliente con el ID proporcionado.
     */
    public Cliente getCliente(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + id));
    }

    /**
     * Obtiene una lista de todos los clientes.
     * @return Una lista de clientes.
     */
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    /**
     * Elimina un cliente por su ID.
     * @param id El ID del cliente a eliminar.
     * @throws IllegalArgumentException Si no existe un cliente con ese ID.
     */
    public void deleteCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + id));
        // Si hay lógica adicional (e.g., verificar dependencias antes de eliminar), agregar aquí
        clienteRepository.delete(cliente);
    }

    /**
     * Actualiza la información de un cliente existente.
     * @param id El ID del cliente a actualizar.
     * @param cliente Los nuevos datos del cliente.
     * @return El cliente actualizado.
     * @throws IllegalArgumentException Si no se encuentra un cliente con el ID proporcionado.
     */
    public Cliente updateCliente(Long id, Cliente cliente) {
        Cliente existingCliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + id));

        if (cliente.getNombre() != null) {
            existingCliente.setNombre(cliente.getNombre());
        }
        if (cliente.getCorreoElectronico() != null) {
            existingCliente.setCorreoElectronico(cliente.getCorreoElectronico());
        }
        if (cliente.getTelefono() != null) {
            existingCliente.setTelefono(cliente.getTelefono());
        }

        return clienteRepository.save(existingCliente);
    }
}

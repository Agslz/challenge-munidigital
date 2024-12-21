package com.washer.demo.services;

import com.washer.demo.entities.Cliente;
import com.washer.demo.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente saveCliente(Cliente cliente) {
        if (cliente.getNombre() == null || cliente.getCorreoElectronico() == null || cliente.getTelefono() == null) {
            throw new IllegalArgumentException("Todos los campos del cliente son obligatorios.");
        }
        return clienteRepository.save(cliente);
    }

    public Cliente getCliente(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + id));
    }

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public void deleteCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new IllegalArgumentException("Cliente no encontrado con ID: " + id);
        }
        clienteRepository.deleteById(id);
    }
}
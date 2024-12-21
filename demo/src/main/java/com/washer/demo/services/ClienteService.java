package com.washer.demo.services;

import com.washer.demo.entities.Cliente;
import com.washer.demo.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> getCliente(Long id) {
        return clienteRepository.findById(id);
    }

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}

package com.washer.demo.services;

import com.washer.demo.entities.Cliente;
import com.washer.demo.entities.Vehiculo;
import com.washer.demo.repositories.ClienteRepository;
import com.washer.demo.repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehiculoService {
    @Autowired
    private VehiculoRepository vehiculoRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    public Vehiculo saveVehiculo(Vehiculo vehiculo, Long clienteId) {
        if (vehiculo.getModelo() == null || vehiculo.getMatricula() == null || vehiculo.getTipo() == null) {
            throw new IllegalArgumentException("Todos los campos del vehículo son obligatorios.");
        }
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
        vehiculo.setCliente(cliente);
        return vehiculoRepository.save(vehiculo);
    }

    public Vehiculo getVehiculo(Long id) {
        return vehiculoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vehículo no encontrado con ID: " + id));
    }

    public List<Vehiculo> getAllVehiculos() {
        return vehiculoRepository.findAll();
    }

    public void deleteVehiculo(Long id) {
        if (!vehiculoRepository.existsById(id)) {
            throw new IllegalArgumentException("Vehículo no encontrado con ID: " + id);
        }
        vehiculoRepository.deleteById(id);
    }

    public Vehiculo updateVehiculo(Long id, Vehiculo vehiculo) {
        // Verifica si el vehículo existe
        Vehiculo existingVehiculo = vehiculoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vehículo no encontrado con ID: " + id));

        // Actualiza los campos necesarios
        if (vehiculo.getModelo() != null) {
            existingVehiculo.setModelo(vehiculo.getModelo());
        }
        if (vehiculo.getMatricula() != null) {
            existingVehiculo.setMatricula(vehiculo.getMatricula());
        }
        if (vehiculo.getTipo() != null) {
            existingVehiculo.setTipo(vehiculo.getTipo());
        }
        if (vehiculo.getCliente() != null && vehiculo.getCliente().getId() != null) {
            Cliente cliente = clienteRepository.findById(vehiculo.getCliente().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + vehiculo.getCliente().getId()));
            existingVehiculo.setCliente(cliente);
        }

        // Guarda los cambios
        return vehiculoRepository.save(existingVehiculo);
    }

}
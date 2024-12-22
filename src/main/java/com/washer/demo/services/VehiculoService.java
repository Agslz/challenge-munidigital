package com.washer.demo.services;

import com.washer.demo.entities.Cliente;
import com.washer.demo.entities.Vehiculo;
import com.washer.demo.repositories.ClienteRepository;
import com.washer.demo.repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Servicio para gestionar operaciones CRUD en la entidad Vehiculo.
 * Este servicio encapsula la lógica de negocio para el manejo de vehículos, incluyendo la asociación con clientes.
 */
@Service
@Transactional
public class VehiculoService {
    @Autowired
    private VehiculoRepository vehiculoRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    /**
     * Guarda un vehículo en la base de datos.
     * @param vehiculo El vehículo a guardar.
     * @param clienteId El ID del cliente asociado al vehículo.
     * @return El vehículo guardado con su ID asignado.
     * @throws IllegalArgumentException Si algún campo obligatorio del vehículo está vacío o si el cliente no se encuentra.
     */
    public Vehiculo saveVehiculo(Vehiculo vehiculo, Long clienteId) {
        if (vehiculo.getModelo() == null || vehiculo.getMatricula() == null || vehiculo.getTipo() == null) {
            throw new IllegalArgumentException("Todos los campos del vehículo son obligatorios.");
        }
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
        vehiculo.setCliente(cliente);
        return vehiculoRepository.save(vehiculo);
    }

    /**
     * Recupera un vehículo por su ID.
     * @param id El ID del vehículo a buscar.
     * @return El vehículo encontrado.
     * @throws IllegalArgumentException Si no se encuentra un vehículo con el ID proporcionado.
     */
    public Vehiculo getVehiculo(Long id) {
        return vehiculoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vehículo no encontrado con ID: " + id));
    }

    /**
     * Obtiene una lista de todos los vehículos.
     * @return Una lista de vehículos.
     */
    public List<Vehiculo> getAllVehiculos() {
        return vehiculoRepository.findAll();
    }

    /**
     * Elimina un vehículo por su ID.
     * @param id El ID del vehículo a eliminar.
     * @throws IllegalArgumentException Si no existe un vehículo con ese ID.
     */
    public void deleteVehiculo(Long id) {
        if (!vehiculoRepository.existsById(id)) {
            throw new IllegalArgumentException("Vehículo no encontrado con ID: " + id);
        }
        vehiculoRepository.deleteById(id);
    }

    /**
     * Actualiza la información de un vehículo existente.
     * @param id El ID del vehículo a actualizar.
     * @param vehiculo Los nuevos datos del vehículo.
     * @return El vehículo actualizado.
     * @throws IllegalArgumentException Si no se encuentra un vehículo con el ID proporcionado o si el cliente asociado no existe.
     */
    public Vehiculo updateVehiculo(Long id, Vehiculo vehiculo) {
        Vehiculo existingVehiculo = vehiculoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vehículo no encontrado con ID: " + id));

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

        return vehiculoRepository.save(existingVehiculo);
    }

}

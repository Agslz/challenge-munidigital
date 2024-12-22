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

    private static final String VEHICULO_NO_ENCONTRADO = "Vehículo no encontrado con ID: ";
    private static final String CLIENTE_NO_ENCONTRADO = "Cliente no encontrado con ID: ";

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    /**
     * Guarda un vehículo en la base de datos.
     * @param vehiculo El vehículo a guardar.
     * @param clienteId El ID del cliente asociado al vehículo.
     * @return El vehículo guardado con su ID asignado.
     */
    public Vehiculo saveVehiculo(Vehiculo vehiculo, Long clienteId) {
        Cliente cliente = validarClienteExistente(clienteId);
        vehiculo.setCliente(cliente);
        return vehiculoRepository.save(vehiculo);
    }

    /**
     * Recupera un vehículo por su ID.
     * @param id El ID del vehículo a buscar.
     * @return El vehículo encontrado.
     */
    public Vehiculo getVehiculo(Long id) {
        return validarVehiculoExistente(id);
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
     */
    public void deleteVehiculo(Long id) {
        Vehiculo vehiculo = validarVehiculoExistente(id);
        vehiculoRepository.delete(vehiculo);
    }

    /**
     * Actualiza la información de un vehículo existente.
     * @param id El ID del vehículo a actualizar.
     * @param vehiculo Los nuevos datos del vehículo.
     * @return El vehículo actualizado.
     */
    public Vehiculo updateVehiculo(Long id, Vehiculo vehiculo) {
        Vehiculo existingVehiculo = validarVehiculoExistente(id);

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
            Cliente cliente = validarClienteExistente(vehiculo.getCliente().getId());
            existingVehiculo.setCliente(cliente);
        }

        return vehiculoRepository.save(existingVehiculo);
    }

    /**
     * Valida la existencia de un cliente por su ID.
     * @param clienteId El ID del cliente a validar.
     * @return El cliente encontrado.
     * @throws IllegalArgumentException Si no se encuentra un cliente con el ID proporcionado.
     */
    private Cliente validarClienteExistente(Long clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException(CLIENTE_NO_ENCONTRADO + clienteId));
    }

    /**
     * Valida la existencia de un vehículo por su ID.
     * @param vehiculoId El ID del vehículo a validar.
     * @return El vehículo encontrado.
     * @throws IllegalArgumentException Si no se encuentra un vehículo con el ID proporcionado.
     */
    private Vehiculo validarVehiculoExistente(Long vehiculoId) {
        return vehiculoRepository.findById(vehiculoId)
                .orElseThrow(() -> new IllegalArgumentException(VEHICULO_NO_ENCONTRADO + vehiculoId));
    }
}

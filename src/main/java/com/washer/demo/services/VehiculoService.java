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
 * Servicio para gestionar las operaciones relacionadas con la entidad Vehiculo.
 * Incluye lógica para crear, leer, actualizar y eliminar vehículos, además de validaciones
 * para garantizar la asociación con clientes existentes.
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
     * Guarda un nuevo vehículo en la base de datos, asociándolo a un cliente existente.
     *
     * @param vehiculo El objeto Vehiculo a guardar.
     * @param clienteId El identificador único del cliente asociado al vehículo.
     * @return El vehículo guardado con su ID asignado automáticamente.
     */
    public Vehiculo saveVehiculo(Vehiculo vehiculo, Long clienteId) {
        Cliente cliente = validarClienteExistente(clienteId);
        vehiculo.setCliente(cliente);
        return vehiculoRepository.save(vehiculo);
    }

    /**
     * Recupera un vehículo por su ID.
     *
     * @param id El identificador único del vehículo a buscar.
     * @return El vehículo encontrado.
     * @throws IllegalArgumentException Si no existe un vehículo con el ID proporcionado.
     */
    public Vehiculo getVehiculo(Long id) {
        return validarVehiculoExistente(id);
    }

    /**
     * Obtiene una lista de todos los vehículos registrados en la base de datos.
     *
     * @return Lista de vehículos existentes.
     */
    public List<Vehiculo> getAllVehiculos() {
        return vehiculoRepository.findAll();
    }

    /**
     * Elimina un vehículo por su ID.
     *
     * @param id El identificador único del vehículo a eliminar.
     * @throws IllegalArgumentException Si no existe un vehículo con el ID proporcionado.
     */
    public void deleteVehiculo(Long id) {
        Vehiculo vehiculo = validarVehiculoExistente(id);
        vehiculoRepository.delete(vehiculo);
    }

    /**
     * Actualiza la información de un vehículo existente.
     *
     * @param id El identificador único del vehículo a actualizar.
     * @param vehiculo Objeto Vehiculo con los datos actualizados.
     * @return El vehículo actualizado.
     * @throws IllegalArgumentException Si no existe un vehículo con el ID proporcionado o si el cliente asociado no existe.
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
     * Valida la existencia de un cliente asociado por su ID.
     *
     * @param clienteId El identificador único del cliente a validar.
     * @return El cliente encontrado.
     * @throws IllegalArgumentException Si no existe un cliente con el ID proporcionado.
     */
    private Cliente validarClienteExistente(Long clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException(CLIENTE_NO_ENCONTRADO + clienteId));
    }

    /**
     * Valida la existencia de un vehículo por su ID.
     *
     * @param vehiculoId El identificador único del vehículo a validar.
     * @return El vehículo encontrado.
     * @throws IllegalArgumentException Si no existe un vehículo con el ID proporcionado.
     */
    private Vehiculo validarVehiculoExistente(Long vehiculoId) {
        return vehiculoRepository.findById(vehiculoId)
                .orElseThrow(() -> new IllegalArgumentException(VEHICULO_NO_ENCONTRADO + vehiculoId));
    }
}

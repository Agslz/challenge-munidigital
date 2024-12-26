package com.washer.demo.services;

import com.washer.demo.entities.Turno;
import com.washer.demo.entities.Vehiculo;
import com.washer.demo.repositories.TurnoRepository;
import com.washer.demo.repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Servicio para gestionar las operaciones relacionadas con la entidad Turno.
 * Incluye la lógica de negocio para crear, leer, actualizar y eliminar turnos, así como validar
 * la asociación de turnos con vehículos existentes.
 */
@Service
@Transactional
public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    /**
     * Guarda un nuevo turno en la base de datos, asociándolo a un vehículo existente.
     *
     * @param turno El objeto Turno a guardar.
     * @return El turno guardado con su ID asignado automáticamente.
     */
    public Turno saveTurno(Turno turno) {
        Vehiculo vehiculo = validarVehiculoExistente(turno.getVehiculo().getId());
        turno.setVehiculo(vehiculo);
        return turnoRepository.save(turno);
    }

    /**
     * Recupera un turno por su ID.
     *
     * @param id El identificador único del turno a buscar.
     * @return El turno encontrado.
     * @throws IllegalArgumentException Si no existe un turno con el ID proporcionado.
     */
    public Turno getTurno(Long id) {
        return turnoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turno no encontrado con ID: " + id));
    }

    /**
     * Obtiene una lista de todos los turnos registrados en la base de datos.
     *
     * @return Lista de turnos existentes.
     */
    public List<Turno> getAllTurnos() {
        return turnoRepository.findAll();
    }

    /**
     * Elimina un turno por su ID.
     *
     * @param id El identificador único del turno a eliminar.
     * @throws IllegalArgumentException Si no existe un turno con el ID proporcionado.
     */
    public void deleteTurno(Long id) {
        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turno no encontrado con ID: " + id));
        turnoRepository.delete(turno);
    }

    /**
     * Actualiza el estado de un turno existente.
     *
     * @param id El identificador único del turno.
     * @param estado El nuevo estado para el turno.
     * @return El turno actualizado.
     * @throws IllegalArgumentException Si no existe un turno con el ID proporcionado.
     */
    public Turno updateEstadoTurno(Long id, String estado) {
        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turno no encontrado con ID: " + id));
        turno.setEstado(estado);
        return turnoRepository.save(turno);
    }

    /**
     * Actualiza la información de un turno existente.
     *
     * @param id El identificador único del turno a actualizar.
     * @param turno Los nuevos datos para actualizar el turno.
     * @return El turno actualizado.
     * @throws IllegalArgumentException Si no existe un turno con el ID proporcionado o si el vehículo asociado no existe.
     */
    public Turno updateTurno(Long id, Turno turno) {
        Turno existingTurno = turnoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turno no encontrado con ID: " + id));

        if (turno.getFechaHora() != null) {
            existingTurno.setFechaHora(turno.getFechaHora());
        }
        if (turno.getEstado() != null) {
            existingTurno.setEstado(turno.getEstado());
        }
        if (turno.getTipoServicio() != null) {
            existingTurno.setTipoServicio(turno.getTipoServicio());
        }
        if (turno.getVehiculo() != null && turno.getVehiculo().getId() != null) {
            Vehiculo vehiculo = validarVehiculoExistente(turno.getVehiculo().getId());
            existingTurno.setVehiculo(vehiculo);
        }

        return turnoRepository.save(existingTurno);
    }

    /**
     * Valida la existencia de un vehículo asociado por su ID.
     *
     * @param vehiculoId El identificador único del vehículo a validar.
     * @return El vehículo encontrado.
     * @throws IllegalArgumentException Si no existe un vehículo con el ID proporcionado.
     */
    private Vehiculo validarVehiculoExistente(Long vehiculoId) {
        return vehiculoRepository.findById(vehiculoId)
                .orElseThrow(() -> new IllegalArgumentException("Vehículo no encontrado con ID: " + vehiculoId));
    }
}

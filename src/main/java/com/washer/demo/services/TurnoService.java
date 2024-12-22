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
 * Servicio para gestionar operaciones CRUD en la entidad Turno.
 * Este servicio encapsula la lógica de negocio para el manejo de turnos, incluyendo la validación de la asociación con vehículos.
 */
@Service
@Transactional
public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    /**
     * Guarda un turno en la base de datos.
     * @param turno El turno a guardar.
     * @return El turno guardado con su ID asignado.
     */
    public Turno saveTurno(Turno turno) {
        Vehiculo vehiculo = validarVehiculoExistente(turno.getVehiculo().getId());
        turno.setVehiculo(vehiculo);
        return turnoRepository.save(turno);
    }

    /**
     * Recupera un turno por su ID.
     * @param id El ID del turno a buscar.
     * @return El turno encontrado.
     * @throws IllegalArgumentException Si no se encuentra un turno con el ID proporcionado.
     */
    public Turno getTurno(Long id) {
        return turnoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turno no encontrado con ID: " + id));
    }

    /**
     * Obtiene una lista de todos los turnos.
     * @return Una lista de turnos.
     */
    public List<Turno> getAllTurnos() {
        return turnoRepository.findAll();
    }

    /**
     * Elimina un turno por su ID.
     * @param id El ID del turno a eliminar.
     * @throws IllegalArgumentException Si no existe un turno con ese ID.
     */
    public void deleteTurno(Long id) {
        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turno no encontrado con ID: " + id));
        turnoRepository.delete(turno);
    }

    /**
     * Actualiza el estado de un turno existente.
     * @param id El ID del turno a actualizar.
     * @param estado El nuevo estado del turno.
     * @return El turno actualizado.
     * @throws IllegalArgumentException Si no se encuentra un turno con el ID proporcionado.
     */
    public Turno updateEstadoTurno(Long id, String estado) {
        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turno no encontrado con ID: " + id));
        turno.setEstado(estado);
        return turnoRepository.save(turno);
    }

    /**
     * Actualiza la información de un turno existente.
     * @param id El ID del turno a actualizar.
     * @param turno Los nuevos datos del turno.
     * @return El turno actualizado.
     * @throws IllegalArgumentException Si no se encuentra un turno con el ID proporcionado o si el vehículo asociado no existe.
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
     * Valida la existencia de un vehículo por su ID.
     * @param vehiculoId El ID del vehículo a validar.
     * @return El vehículo encontrado.
     * @throws IllegalArgumentException Si no se encuentra el vehículo con el ID proporcionado.
     */
    private Vehiculo validarVehiculoExistente(Long vehiculoId) {
        return vehiculoRepository.findById(vehiculoId)
                .orElseThrow(() -> new IllegalArgumentException("Vehículo no encontrado con ID: " + vehiculoId));
    }
}

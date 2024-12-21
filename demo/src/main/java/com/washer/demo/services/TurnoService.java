package com.washer.demo.services;

import com.washer.demo.entities.Turno;
import com.washer.demo.entities.Vehiculo;
import com.washer.demo.repositories.TurnoRepository;
import com.washer.demo.repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TurnoService {
    @Autowired
    private TurnoRepository turnoRepository;
    @Autowired
    private VehiculoRepository vehiculoRepository;

    public Turno saveTurno(Turno turno) {
        if (turno.getVehiculo() == null || turno.getVehiculo().getId() == null) {
            throw new IllegalArgumentException("El vehículo asociado al turno es obligatorio.");
        }
        Vehiculo vehiculo = vehiculoRepository.findById(turno.getVehiculo().getId())
                .orElseThrow(() -> new IllegalArgumentException("Vehículo no encontrado"));
        turno.setVehiculo(vehiculo);
        return turnoRepository.save(turno);
    }

    public Turno getTurno(Long id) {
        return turnoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turno no encontrado con ID: " + id));
    }

    public List<Turno> getAllTurnos() {
        return turnoRepository.findAll();
    }

    public void deleteTurno(Long id) {
        if (!turnoRepository.existsById(id)) {
            throw new IllegalArgumentException("Turno no encontrado con ID: " + id);
        }
        turnoRepository.deleteById(id);
    }

    public Turno updateEstadoTurno(Long id, String estado) {
        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turno no encontrado con ID: " + id));
        turno.setEstado(estado);
        return turnoRepository.save(turno);
    }

    public Turno updateTurno(Long id, Turno turno) {
        // Verifica si el turno existe
        Turno existingTurno = turnoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turno no encontrado con ID: " + id));

        // Actualiza los campos necesarios
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
            Vehiculo vehiculo = vehiculoRepository.findById(turno.getVehiculo().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Vehículo no encontrado con ID: " + turno.getVehiculo().getId()));
            existingTurno.setVehiculo(vehiculo);
        }

        // Guarda los cambios
        return turnoRepository.save(existingTurno);
    }

}

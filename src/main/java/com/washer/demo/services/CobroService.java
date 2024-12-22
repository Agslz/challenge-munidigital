package com.washer.demo.services;

import com.washer.demo.entities.Cobro;
import com.washer.demo.entities.Turno;
import com.washer.demo.repositories.CobroRepository;
import com.washer.demo.repositories.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Servicio para gestionar operaciones CRUD en la entidad Cobro y actualizar el estado de Turno.
 * Este servicio encapsula la lógica de negocio para el manejo de cobros.
 */
@Service
@Transactional
public class CobroService {
    @Autowired
    private CobroRepository cobroRepository;
    @Autowired
    private TurnoRepository turnoRepository;

    /**
     * Guarda un cobro en la base de datos.
     * @param cobro El cobro a guardar.
     * @return El cobro guardado con su ID asignado.
     * @throws IllegalArgumentException Si el turno asociado no está completado o no se encuentra.
     */
    public Cobro saveCobro(Cobro cobro) {
        Turno turno = turnoRepository.findById(cobro.getTurno().getId())
                .orElseThrow(() -> new IllegalArgumentException("Turno no encontrado"));
        if (!"completado".equals(turno.getEstado())) {
            throw new IllegalArgumentException("El turno debe estar completado para registrar un cobro.");
        }
        cobro.setTurno(turno);
        return cobroRepository.save(cobro);
    }

    /**
     * Recupera un cobro por su ID.
     * @param id El ID del cobro a buscar.
     * @return El cobro encontrado.
     * @throws IllegalArgumentException Si no se encuentra un cobro con el ID proporcionado.
     */
    public Cobro getCobro(Long id) {
        return cobroRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cobro no encontrado con ID: " + id));
    }

    /**
     * Obtiene una lista de todos los cobros registrados.
     * @return Una lista de cobros.
     */
    public List<Cobro> getAllCobros() {
        return cobroRepository.findAll();
    }

    /**
     * Elimina un cobro por su ID.
     * @param id El ID del cobro a eliminar.
     * @throws IllegalArgumentException Si no existe un cobro con ese ID.
     */
    public void deleteCobro(Long id) {
        if (!cobroRepository.existsById(id)) {
            throw new IllegalArgumentException("Cobro no encontrado con ID: " + id);
        }
        cobroRepository.deleteById(id);
    }

    /**
     * Actualiza el estado de un turno.
     * @param turnoId El ID del turno a actualizar.
     * @param estado El nuevo estado del turno.
     * @return El turno actualizado.
     * @throws IllegalArgumentException Si no se encuentra un turno con el ID proporcionado.
     */
    public Turno updateEstadoTurno(Long turnoId, String estado) {
        Turno turno = turnoRepository.findById(turnoId)
                .orElseThrow(() -> new IllegalArgumentException("Turno no encontrado"));
        turno.setEstado(estado);
        return turnoRepository.save(turno);
    }

    /**
     * Actualiza la información de un cobro existente.
     * @param id El ID del cobro a actualizar.
     * @param cobro Los nuevos datos del cobro.
     * @return El cobro actualizado.
     * @throws IllegalArgumentException Si no se encuentra un cobro con el ID proporcionado o si el turno asociado no está completado.
     */
    public Cobro updateCobro(Long id, Cobro cobro) {
        Cobro existingCobro = cobroRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cobro no encontrado con ID: " + id));

        if (cobro.getMonto() != null) {
            existingCobro.setMonto(cobro.getMonto());
        }
        if (cobro.getFecha() != null) {
            existingCobro.setFecha(cobro.getFecha());
        }
        if (cobro.getTurno() != null && cobro.getTurno().getId() != null) {
            Turno turno = turnoRepository.findById(cobro.getTurno().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Turno no encontrado con ID: " + cobro.getTurno().getId()));
            if (!"completado".equals(turno.getEstado())) {
                throw new IllegalArgumentException("El turno debe estar completado para asociarlo al cobro.");
            }
            existingCobro.setTurno(turno);
        }

        return cobroRepository.save(existingCobro);
    }

}

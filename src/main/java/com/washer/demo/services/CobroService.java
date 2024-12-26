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
 * Servicio para gestionar las operaciones relacionadas con la entidad Cobro.
 * Incluye la lógica de negocio para crear, leer, actualizar y eliminar cobros,
 * y permite actualizar la información asociada a los turnos.
 */
@Service
@Transactional
public class CobroService {

    @Autowired
    private CobroRepository cobroRepository;

    @Autowired
    private TurnoRepository turnoRepository;

    /**
     * Guarda un nuevo cobro en la base de datos.
     *
     * @param cobro El objeto Cobro a guardar.
     * @return El cobro guardado con su ID asignado automáticamente.
     */
    public Cobro saveCobro(Cobro cobro) {
        return cobroRepository.save(cobro);
    }

    /**
     * Recupera un cobro por su ID.
     *
     * @param id El identificador único del cobro a buscar.
     * @return El cobro encontrado.
     * @throws IllegalArgumentException Si no existe un cobro con el ID proporcionado.
     */
    public Cobro getCobro(Long id) {
        return cobroRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cobro no encontrado con ID: " + id));
    }

    /**
     * Obtiene una lista de todos los cobros registrados en la base de datos.
     *
     * @return Lista de cobros existentes.
     */
    public List<Cobro> getAllCobros() {
        return cobroRepository.findAll();
    }

    /**
     * Elimina un cobro por su ID.
     *
     * @param id El identificador único del cobro a eliminar.
     * @throws IllegalArgumentException Si no existe un cobro con el ID proporcionado.
     */
    public void deleteCobro(Long id) {
        Cobro cobro = cobroRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cobro no encontrado con ID: " + id));
        cobroRepository.delete(cobro);
    }

    /**
     * Actualiza la información de un cobro existente.
     *
     * @param id El identificador único del cobro a actualizar.
     * @param cobro Objeto Cobro con los datos actualizados.
     * @return El cobro actualizado.
     * @throws IllegalArgumentException Si no existe un cobro con el ID proporcionado.
     */
    public Cobro updateCobro(Long id, Cobro cobro) {
        Cobro existingCobro = cobroRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cobro no encontrado con ID: " + id));

        // Actualiza los campos no nulos del cobro proporcionado
        if (cobro.getMonto() != null) {
            existingCobro.setMonto(cobro.getMonto());
        }
        if (cobro.getFecha() != null) {
            existingCobro.setFecha(cobro.getFecha());
        }
        if (cobro.getTurno() != null) {
            existingCobro.setTurno(cobro.getTurno());
        }

        return cobroRepository.save(existingCobro);
    }
}

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
     */
    public Cobro saveCobro(Cobro cobro) {
        // Se elimina la validación del turno
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
     */
    public void deleteCobro(Long id) {
        Cobro cobro = cobroRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cobro no encontrado con ID: " + id));
        cobroRepository.delete(cobro);
    }

    /**
     * Actualiza la información de un cobro existente.
     * @param id El ID del cobro a actualizar.
     * @param cobro Los nuevos datos del cobro.
     * @return El cobro actualizado.
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
        if (cobro.getTurno() != null) {
            existingCobro.setTurno(cobro.getTurno());
        }

        return cobroRepository.save(existingCobro);
    }
}

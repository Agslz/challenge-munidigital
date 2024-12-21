package com.washer.demo.services;

import com.washer.demo.entities.Cobro;
import com.washer.demo.entities.Turno;
import com.washer.demo.repositories.CobroRepository;
import com.washer.demo.repositories.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CobroService {
    @Autowired
    private CobroRepository cobroRepository;
    @Autowired
    private TurnoRepository turnoRepository;

    public Cobro saveCobro(Cobro cobro) {
        Turno turno = turnoRepository.findById(cobro.getTurno().getId())
                .orElseThrow(() -> new IllegalArgumentException("Turno no encontrado"));
        if (!"completado".equals(turno.getEstado())) {
            throw new IllegalArgumentException("El turno debe estar completado para registrar un cobro.");
        }
        cobro.setTurno(turno);
        return cobroRepository.save(cobro);
    }

    public Cobro getCobro(Long id) {
        return cobroRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cobro no encontrado con ID: " + id));
    }

    public List<Cobro> getAllCobros() {
        return cobroRepository.findAll();
    }

    public void deleteCobro(Long id) {
        if (!cobroRepository.existsById(id)) {
            throw new IllegalArgumentException("Cobro no encontrado con ID: " + id);
        }
        cobroRepository.deleteById(id);
    }

    public Turno updateEstadoTurno(Long turnoId, String estado) {
        Turno turno = turnoRepository.findById(turnoId)
                .orElseThrow(() -> new IllegalArgumentException("Turno no encontrado"));
        turno.setEstado(estado);
        return turnoRepository.save(turno);
    }
}
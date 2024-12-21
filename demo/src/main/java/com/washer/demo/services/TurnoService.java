package com.washer.demo.services;

import com.washer.demo.entities.Turno;
import com.washer.demo.repositories.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TurnoService {
    @Autowired
    private TurnoRepository turnoRepository;

    public Turno saveTurno(Turno turno) {
        return turnoRepository.save(turno);
    }

    public Optional<Turno> getTurno(Long id) {
        return turnoRepository.findById(id);
    }

    public List<Turno> getAllTurnos() {
        return turnoRepository.findAll();
    }

    public void deleteTurno(Long id) {
        turnoRepository.deleteById(id);
    }
}

package com.washer.demo.services;

import com.washer.demo.entities.Cobro;
import com.washer.demo.repositories.CobroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CobroService {
    @Autowired
    private CobroRepository cobroRepository;

    public Cobro saveCobro(Cobro cobro) {
        return cobroRepository.save(cobro);
    }

    public Optional<Cobro> getCobro(Long id) {
        return cobroRepository.findById(id);
    }

    public List<Cobro> getAllCobros() {
        return cobroRepository.findAll();
    }

    public void deleteCobro(Long id) {
        cobroRepository.deleteById(id);
    }
}

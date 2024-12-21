package com.washer.demo.services;

import com.washer.demo.entities.Vehiculo;
import com.washer.demo.repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehiculoService {
    @Autowired
    private VehiculoRepository vehiculoRepository;

    public Vehiculo saveVehiculo(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    public Optional<Vehiculo> getVehiculo(Long id) {
        return vehiculoRepository.findById(id);
    }

    public List<Vehiculo> getAllVehiculos() {
        return vehiculoRepository.findAll();
    }

    public void deleteVehiculo(Long id) {
        vehiculoRepository.deleteById(id);
    }
}

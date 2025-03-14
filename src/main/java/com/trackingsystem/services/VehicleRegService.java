package com.trackingsystem.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trackingsystem.models.VehicleReg;
import com.trackingsystem.repositories.VehicleRegRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleRegService {

    @Autowired
    private VehicleRegRepository vehicleRegRepository;

    // Create a new vehicle registration
    public VehicleReg createVehicleReg(VehicleReg vehicleReg) {
        return vehicleRegRepository.save(vehicleReg);
    }

    // Get all registered vehicles
    public List<VehicleReg> getAllVehicles() {
        return vehicleRegRepository.findAll();
    }

    // Get a single vehicle registration by its registration number
    public Optional<VehicleReg> getVehicleByRegNum(Long vehicleRegNum) {
        return vehicleRegRepository.findById(vehicleRegNum);
    }

    // Update vehicle details
    public VehicleReg updateVehicle(Long vehicleRegNum, VehicleReg updatedVehicle) {
        return vehicleRegRepository.findById(vehicleRegNum)
                .map(vehicle -> {
                    vehicle.setVehicleName(updatedVehicle.getVehicleName());
                    vehicle.setEngineCapacity(updatedVehicle.getEngineCapacity());
                    vehicle.setVehicleState(updatedVehicle.getVehicleState());
                    vehicle.setVehicleOwner(updatedVehicle.getVehicleOwner());
                    return vehicleRegRepository.save(vehicle);
                })
                .orElseThrow(() -> new RuntimeException("Vehicle not found with RegNum: " + vehicleRegNum));
    }

    // Delete a vehicle registration
    public void deleteVehicle(Long vehicleRegNum) {
        vehicleRegRepository.deleteById(vehicleRegNum);
    }
}


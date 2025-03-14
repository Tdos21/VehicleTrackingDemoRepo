package com.trackingsystem.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.trackingsystem.models.VehicleReg;
import com.trackingsystem.services.VehicleRegService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleRegController {

    @Autowired
    private VehicleRegService vehicleRegService;

    // Create a new vehicle registration
    @PostMapping
    public ResponseEntity<VehicleReg> createVehicle(@RequestBody VehicleReg vehicleReg) {
        VehicleReg savedVehicle = vehicleRegService.createVehicleReg(vehicleReg);
        return ResponseEntity.ok(savedVehicle);
    }

    // Get all vehicles
    @GetMapping
    public ResponseEntity<List<VehicleReg>> getAllVehicles() {
        return ResponseEntity.ok(vehicleRegService.getAllVehicles());
    }

    // Get a vehicle by registration number
    @GetMapping("/{vehicleRegNum}")
    public ResponseEntity<VehicleReg> getVehicleByRegNum(@PathVariable Long vehicleRegNum) {
        Optional<VehicleReg> vehicle = vehicleRegService.getVehicleByRegNum(vehicleRegNum);
        return vehicle.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update an existing vehicle registration
    @PutMapping("/{vehicleRegNum}")
    public ResponseEntity<VehicleReg> updateVehicle(@PathVariable Long vehicleRegNum, @RequestBody VehicleReg updatedVehicle) {
        try {
            VehicleReg vehicle = vehicleRegService.updateVehicle(vehicleRegNum, updatedVehicle);
            return ResponseEntity.ok(vehicle);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a vehicle registration
    @DeleteMapping("/{vehicleRegNum}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long vehicleRegNum) {
        vehicleRegService.deleteVehicle(vehicleRegNum);
        return ResponseEntity.noContent().build();
    }
}


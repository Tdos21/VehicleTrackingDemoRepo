package com.trackingsystem.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.trackingsystem.models.VehicleOwner;
import com.trackingsystem.services.VehicleOwnerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/vehicle-owners")
public class VehicleOwnerController {

    @Autowired
    private VehicleOwnerService vehicleOwnerService;

    // Create a new vehicle owner
    @PostMapping(path="/create")
    public ResponseEntity<VehicleOwner> createOwner(@RequestBody VehicleOwner vehicleOwner) {
        VehicleOwner savedOwner = vehicleOwnerService.createOwner(vehicleOwner);
        return ResponseEntity.ok(savedOwner);
    }

    // Get all vehicle owners
    @GetMapping("/all")
    public ResponseEntity<List<VehicleOwner>> getAllOwners() {
        return ResponseEntity.ok(vehicleOwnerService.getAllOwners());
    }

    // Get owner by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<VehicleOwner> getOwnerById(@PathVariable Long id) {
        Optional<VehicleOwner> owner = vehicleOwnerService.getOwnerById(id);
        return owner.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update an existing vehicle owner
    @PutMapping("/update/{id}")
    public ResponseEntity<VehicleOwner> updateOwner(@PathVariable Long id, @RequestBody VehicleOwner updatedOwner) {
        try {
            VehicleOwner owner = vehicleOwnerService.updateOwner(id, updatedOwner);
            return ResponseEntity.ok(owner);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a vehicle owner
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id) {
        vehicleOwnerService.deleteOwner(id);
        return ResponseEntity.noContent().build();
    }
}


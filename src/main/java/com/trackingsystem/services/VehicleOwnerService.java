package com.trackingsystem.services;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trackingsystem.models.VehicleOwner;
import com.trackingsystem.repositories.VehicleOwnerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleOwnerService {

    @Autowired
    private VehicleOwnerRepository vehicleOwnerRepository;

    // Create a new vehicle owner
    public VehicleOwner createOwner(VehicleOwner vehicleOwner) {
        return vehicleOwnerRepository.save(vehicleOwner);
    }

    // Get all vehicle owners
    public List<VehicleOwner> getAllOwners() {
        return vehicleOwnerRepository.findAll();
    }

    // Get a single owner by ID
    public Optional<VehicleOwner> getOwnerById(Long ownerId) {
        return vehicleOwnerRepository.findById(ownerId);
    }

    // Update vehicle owner details
    public VehicleOwner updateOwner(Long ownerId, VehicleOwner updatedOwner) {
        return vehicleOwnerRepository.findById(ownerId)
                .map(owner -> {
                    owner.setOwnerFullName(updatedOwner.getOwnerFullName());
                    owner.setEmail(updatedOwner.getEmail());
                    owner.setOwnerAddress(updatedOwner.getOwnerAddress());
                    owner.setOwnerCellNumber(updatedOwner.getOwnerCellNumber());
                    return vehicleOwnerRepository.save(owner);
                })
                .orElseThrow(() -> new RuntimeException("Owner not found with ID: " + ownerId));
    }

    // Delete a vehicle owner
    public void deleteOwner(Long ownerId) {
        vehicleOwnerRepository.deleteById(ownerId);
    }
}


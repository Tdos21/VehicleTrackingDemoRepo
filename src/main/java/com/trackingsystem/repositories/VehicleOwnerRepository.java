package com.trackingsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trackingsystem.models.VehicleOwner;

public interface VehicleOwnerRepository extends JpaRepository<VehicleOwner, Long> {

}

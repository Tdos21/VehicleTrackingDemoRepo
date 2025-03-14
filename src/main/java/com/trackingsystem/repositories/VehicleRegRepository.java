package com.trackingsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trackingsystem.models.VehicleReg;

public interface VehicleRegRepository extends JpaRepository<VehicleReg, Long> {

}

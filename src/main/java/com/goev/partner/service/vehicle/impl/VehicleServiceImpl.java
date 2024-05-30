package com.goev.partner.service.vehicle.impl;


import com.goev.partner.dto.vehicle.detail.VehicleDto;
import com.goev.partner.repository.vehicle.detail.VehicleRepository;
import com.goev.partner.service.vehicle.VehicleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;


    @Override
    public VehicleDto getVehicleDetails(String vehicleUUID) {
        return null;
    }
}

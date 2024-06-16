package com.goev.partner.service.vehicle.impl;


import com.goev.lib.exceptions.ResponseException;
import com.goev.partner.dao.vehicle.detail.VehicleDao;
import com.goev.partner.dto.vehicle.VehicleViewDto;
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
    public VehicleViewDto getVehicleDetails(String vehicleUUID) {
        VehicleDao vehicle = vehicleRepository.findByUUID(vehicleUUID);
        if (vehicle == null)
            throw new ResponseException("No vehicle found for uuid:" + vehicleUUID);
        return VehicleViewDto.fromDao(vehicle);

    }
}

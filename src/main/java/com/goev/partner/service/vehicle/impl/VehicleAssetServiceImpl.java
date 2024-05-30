package com.goev.partner.service.vehicle.impl;


import com.goev.partner.repository.vehicle.detail.VehicleRepository;
import com.goev.partner.service.vehicle.VehicleAssetService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@AllArgsConstructor
public class VehicleAssetServiceImpl implements VehicleAssetService {

    private final VehicleRepository vehicleRepository;


}

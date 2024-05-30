package com.goev.partner.service.vehicle;

import com.goev.partner.dto.vehicle.detail.VehicleDto;

public interface VehicleService {

    VehicleDto getVehicleDetails(String vehicleUUID);
}

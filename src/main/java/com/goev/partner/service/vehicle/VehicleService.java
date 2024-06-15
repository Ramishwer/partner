package com.goev.partner.service.vehicle;

import com.goev.partner.dto.vehicle.VehicleViewDto;


public interface VehicleService {

    VehicleViewDto getVehicleDetails(String vehicleUUID);
}

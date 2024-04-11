package com.goev.partner.repository.vehicle.detail;

import com.goev.partner.dao.vehicle.detail.VehicleManufacturerDao;

import java.util.List;

public interface VehicleManufacturerRepository {
    VehicleManufacturerDao save(VehicleManufacturerDao vehicleManufacturer);
    VehicleManufacturerDao update(VehicleManufacturerDao vehicleManufacturer);
    void delete(Integer id);
    VehicleManufacturerDao findByUUID(String uuid);
    VehicleManufacturerDao findById(Integer id);
    List<VehicleManufacturerDao> findAllByIds(List<Integer> ids);
    List<VehicleManufacturerDao> findAll();
}



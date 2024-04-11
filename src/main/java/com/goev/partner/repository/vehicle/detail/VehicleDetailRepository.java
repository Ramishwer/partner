package com.goev.partner.repository.vehicle.detail;

import com.goev.partner.dao.vehicle.detail.VehicleDetailDao;

import java.util.List;

public interface VehicleDetailRepository {
    VehicleDetailDao save(VehicleDetailDao vehicleDetail);
    VehicleDetailDao update(VehicleDetailDao vehicleDetail);
    void delete(Integer id);
    VehicleDetailDao findByUUID(String uuid);
    VehicleDetailDao findById(Integer id);
    List<VehicleDetailDao> findAllByIds(List<Integer> ids);
    List<VehicleDetailDao> findAll();
}
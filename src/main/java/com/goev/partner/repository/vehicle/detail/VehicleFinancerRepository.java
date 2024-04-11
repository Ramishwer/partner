package com.goev.partner.repository.vehicle.detail;

import com.goev.partner.dao.vehicle.detail.VehicleFinancerDao;

import java.util.List;

public interface VehicleFinancerRepository {
    VehicleFinancerDao save(VehicleFinancerDao financer);
    VehicleFinancerDao update(VehicleFinancerDao financer);
    void delete(Integer id);
    VehicleFinancerDao findByUUID(String uuid);
    VehicleFinancerDao findById(Integer id);
    List<VehicleFinancerDao> findAllByIds(List<Integer> ids);
    List<VehicleFinancerDao> findAll();
}


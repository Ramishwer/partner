package com.goev.partner.repository.vehicle.detail;

import com.goev.partner.dao.vehicle.detail.VehicleCategoryDao;

import java.util.List;

public interface VehicleCategoryRepository {
    VehicleCategoryDao save(VehicleCategoryDao category);

    VehicleCategoryDao update(VehicleCategoryDao category);

    void delete(Integer id);

    VehicleCategoryDao findByUUID(String uuid);

    VehicleCategoryDao findById(Integer id);

    List<VehicleCategoryDao> findAllByIds(List<Integer> ids);

    List<VehicleCategoryDao> findAllActive();
}
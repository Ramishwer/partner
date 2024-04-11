package com.goev.partner.repository.vehicle.asset;

import com.goev.partner.dao.vehicle.asset.VehicleAssetDao;

import java.util.List;

public interface VehicleAssetRepository {
    VehicleAssetDao save(VehicleAssetDao partner);
    VehicleAssetDao update(VehicleAssetDao partner);
    void delete(Integer id);
    VehicleAssetDao findByUUID(String uuid);
    VehicleAssetDao findById(Integer id);
    List<VehicleAssetDao> findAllByIds(List<Integer> ids);
    List<VehicleAssetDao> findAll();
}
package com.goev.partner.repository.vehicle.asset;

import com.goev.partner.dao.vehicle.asset.VehicleAssetMappingDao;

import java.util.List;

public interface VehicleAssetMappingRepository {
    VehicleAssetMappingDao save(VehicleAssetMappingDao partner);
    VehicleAssetMappingDao update(VehicleAssetMappingDao partner);
    void delete(Integer id);
    VehicleAssetMappingDao findByUUID(String uuid);
    VehicleAssetMappingDao findById(Integer id);
    List<VehicleAssetMappingDao> findAllByIds(List<Integer> ids);
    List<VehicleAssetMappingDao> findAll();

    List<VehicleAssetMappingDao> findAllByVehicleId(Integer vehicleId);
}
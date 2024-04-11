package com.goev.partner.repository.vehicle.asset;

import com.goev.partner.dao.vehicle.asset.VehicleTransferDetailDao;

import java.util.List;

public interface VehicleTransferDetailRepository {
    VehicleTransferDetailDao save(VehicleTransferDetailDao partner);
    VehicleTransferDetailDao update(VehicleTransferDetailDao partner);
    void delete(Integer id);
    VehicleTransferDetailDao findByUUID(String uuid);
    VehicleTransferDetailDao findById(Integer id);
    List<VehicleTransferDetailDao> findAllByIds(List<Integer> ids);
    List<VehicleTransferDetailDao> findAll();
}

package com.goev.partner.repository.location;

import com.goev.partner.dao.location.LocationDetailDao;

import java.util.List;

public interface LocationDetailRepository {
    LocationDetailDao save(LocationDetailDao partner);
    LocationDetailDao update(LocationDetailDao partner);
    void delete(Integer id);
    LocationDetailDao findByUUID(String uuid);
    LocationDetailDao findById(Integer id);
    List<LocationDetailDao> findAllByIds(List<Integer> ids);
    List<LocationDetailDao> findAll();
}
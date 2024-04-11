package com.goev.partner.repository.location;

import com.goev.partner.dao.location.LocationAttributeDao;

import java.util.List;

public interface LocationAttributeRepository {
    LocationAttributeDao save(LocationAttributeDao partner);
    LocationAttributeDao update(LocationAttributeDao partner);
    void delete(Integer id);
    LocationAttributeDao findByUUID(String uuid);
    LocationAttributeDao findById(Integer id);
    List<LocationAttributeDao> findAllByIds(List<Integer> ids);
    List<LocationAttributeDao> findAll();
}
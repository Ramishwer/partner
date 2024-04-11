package com.goev.partner.repository.system.instance;

import com.goev.partner.dao.system.instance.SystemInstanceDao;

import java.util.List;

public interface SystemInstanceRepository {
    SystemInstanceDao save(SystemInstanceDao instance);
    SystemInstanceDao update(SystemInstanceDao instance);
    void delete(Integer id);
    SystemInstanceDao findByUUID(String uuid);
    SystemInstanceDao findById(Integer id);
    List<SystemInstanceDao> findAllByIds(List<Integer> ids);
    List<SystemInstanceDao> findAll();
}
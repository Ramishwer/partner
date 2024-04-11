package com.goev.partner.repository.system.log;

import com.goev.partner.dao.system.log.SystemLogDao;

import java.util.List;

public interface SystemLogRepository {
    SystemLogDao save(SystemLogDao log);
    SystemLogDao update(SystemLogDao log);
    void delete(Integer id);
    SystemLogDao findByUUID(String uuid);
    SystemLogDao findById(Integer id);
    List<SystemLogDao> findAllByIds(List<Integer> ids);
    List<SystemLogDao> findAll();
}
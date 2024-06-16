package com.goev.partner.repository.business;

import com.goev.partner.dao.business.BusinessClientDao;

import java.util.List;

public interface BusinessClientRepository {
    BusinessClientDao save(BusinessClientDao client);

    BusinessClientDao update(BusinessClientDao client);

    void delete(Integer id);

    BusinessClientDao findByUUID(String uuid);

    BusinessClientDao findById(Integer id);

    List<BusinessClientDao> findAllByIds(List<Integer> ids);

    List<BusinessClientDao> findAllActive();
}
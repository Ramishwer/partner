package com.goev.partner.repository.partner.app;

import com.goev.partner.dao.partner.app.PartnerAppPropertyDao;

import java.util.List;

public interface PartnerAppPropertyRepository {
    PartnerAppPropertyDao save(PartnerAppPropertyDao partner);
    PartnerAppPropertyDao update(PartnerAppPropertyDao partner);
    void delete(Integer id);
    PartnerAppPropertyDao findByUUID(String uuid);
    PartnerAppPropertyDao findById(Integer id);
    List<PartnerAppPropertyDao> findAllByIds(List<Integer> ids);
    List<PartnerAppPropertyDao> findAll();
}
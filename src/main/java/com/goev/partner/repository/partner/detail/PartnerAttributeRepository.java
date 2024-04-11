package com.goev.partner.repository.partner.detail;

import com.goev.partner.dao.partner.detail.PartnerAttributeDao;

import java.util.List;

public interface PartnerAttributeRepository {
    PartnerAttributeDao save(PartnerAttributeDao attribute);
    PartnerAttributeDao update(PartnerAttributeDao attribute);
    void delete(Integer id);
    PartnerAttributeDao findByUUID(String uuid);
    PartnerAttributeDao findById(Integer id);
    List<PartnerAttributeDao> findAllByIds(List<Integer> ids);
    List<PartnerAttributeDao> findAll();
}
package com.goev.partner.repository.partner.detail;

import com.goev.partner.dao.partner.detail.PartnerActionDao;

import java.util.List;

public interface PartnerActionRepository {
    PartnerActionDao save(PartnerActionDao action);

    PartnerActionDao update(PartnerActionDao action);

    void delete(Integer id);

    PartnerActionDao findByUUID(String uuid);

    PartnerActionDao findById(Integer id);

    List<PartnerActionDao> findAllByIds(List<Integer> ids);

    List<PartnerActionDao> findAllActive();
}
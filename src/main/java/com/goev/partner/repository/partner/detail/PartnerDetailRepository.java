package com.goev.partner.repository.partner.detail;

import com.goev.partner.dao.partner.detail.PartnerDetailDao;

import java.util.List;

public interface PartnerDetailRepository {
    PartnerDetailDao save(PartnerDetailDao partnerDetail);
    PartnerDetailDao update(PartnerDetailDao partnerDetail);
    void delete(Integer id);
    PartnerDetailDao findByUUID(String uuid);
    PartnerDetailDao findById(Integer id);
    List<PartnerDetailDao> findAllByIds(List<Integer> ids);
    List<PartnerDetailDao> findAll();
}
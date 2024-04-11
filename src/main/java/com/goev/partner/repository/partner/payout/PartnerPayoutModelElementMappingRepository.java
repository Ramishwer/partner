package com.goev.partner.repository.partner.payout;

import com.goev.partner.dao.partner.payout.PartnerPayoutModelElementMappingDao;

import java.util.List;

public interface PartnerPayoutModelElementMappingRepository {
    PartnerPayoutModelElementMappingDao save(PartnerPayoutModelElementMappingDao partner);
    PartnerPayoutModelElementMappingDao update(PartnerPayoutModelElementMappingDao partner);
    void delete(Integer id);
    PartnerPayoutModelElementMappingDao findByUUID(String uuid);
    PartnerPayoutModelElementMappingDao findById(Integer id);
    List<PartnerPayoutModelElementMappingDao> findAllByIds(List<Integer> ids);
    List<PartnerPayoutModelElementMappingDao> findAll();
}
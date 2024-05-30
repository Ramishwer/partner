package com.goev.partner.repository.partner.duty;

import com.goev.partner.dao.partner.duty.PartnerDutyDao;

import java.util.List;

public interface PartnerDutyRepository {
    PartnerDutyDao save(PartnerDutyDao partner);
    PartnerDutyDao update(PartnerDutyDao partner);
    void delete(Integer id);
    PartnerDutyDao findByUUID(String uuid);
    PartnerDutyDao findById(Integer id);
    List<PartnerDutyDao> findAllByIds(List<Integer> ids);
    List<PartnerDutyDao> findAll();

    List<PartnerDutyDao> findAllByPartnerId(Integer id);
}
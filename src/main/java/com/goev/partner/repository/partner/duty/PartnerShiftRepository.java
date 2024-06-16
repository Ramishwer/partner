package com.goev.partner.repository.partner.duty;

import com.goev.partner.dao.partner.duty.PartnerShiftDao;

import java.util.List;

public interface PartnerShiftRepository {
    PartnerShiftDao save(PartnerShiftDao partner);

    PartnerShiftDao update(PartnerShiftDao partner);

    void delete(Integer id);

    PartnerShiftDao findByUUID(String uuid);

    PartnerShiftDao findById(Integer id);

    List<PartnerShiftDao> findAllByIds(List<Integer> ids);

    List<PartnerShiftDao> findAllActive();

    List<PartnerShiftDao> findAllByPartnerId(Integer id);
}
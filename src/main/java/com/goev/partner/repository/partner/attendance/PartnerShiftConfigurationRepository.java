package com.goev.partner.repository.partner.attendance;

import com.goev.partner.dao.partner.attendance.PartnerShiftConfigurationDao;

import java.util.List;

public interface PartnerShiftConfigurationRepository {
    PartnerShiftConfigurationDao save(PartnerShiftConfigurationDao partner);
    PartnerShiftConfigurationDao update(PartnerShiftConfigurationDao partner);
    void delete(Integer id);
    PartnerShiftConfigurationDao findByUUID(String uuid);
    PartnerShiftConfigurationDao findById(Integer id);
    List<PartnerShiftConfigurationDao> findAllByIds(List<Integer> ids);
    List<PartnerShiftConfigurationDao> findAll();
}
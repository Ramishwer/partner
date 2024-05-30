package com.goev.partner.repository.partner.duty;

import com.goev.partner.dao.partner.duty.PartnerLeaveDao;

import java.util.List;

public interface PartnerLeaveRepository {
    PartnerLeaveDao save(PartnerLeaveDao partner);
    PartnerLeaveDao update(PartnerLeaveDao partner);
    void delete(Integer id);
    PartnerLeaveDao findByUUID(String uuid);
    PartnerLeaveDao findById(Integer id);
    List<PartnerLeaveDao> findAllByIds(List<Integer> ids);
    List<PartnerLeaveDao> findAll();

    List<PartnerLeaveDao> findAllByPartnerId(Integer id);
}
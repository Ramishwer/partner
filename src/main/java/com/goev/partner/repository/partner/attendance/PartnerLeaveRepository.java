package com.goev.partner.repository.partner.attendance;

import com.goev.partner.dao.partner.attendance.PartnerLeaveDao;

import java.util.List;

public interface PartnerLeaveRepository {
    PartnerLeaveDao save(PartnerLeaveDao partner);
    PartnerLeaveDao update(PartnerLeaveDao partner);
    void delete(Integer id);
    PartnerLeaveDao findByUUID(String uuid);
    PartnerLeaveDao findById(Integer id);
    List<PartnerLeaveDao> findAllByIds(List<Integer> ids);
    List<PartnerLeaveDao> findAll();
}
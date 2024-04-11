package com.goev.partner.repository.business;

import com.goev.partner.dao.business.BusinessSegmentDao;

import java.util.List;

public interface BusinessSegmentRepository {
    BusinessSegmentDao save(BusinessSegmentDao segment);
    BusinessSegmentDao update(BusinessSegmentDao segment);
    void delete(Integer id);
    BusinessSegmentDao findByUUID(String uuid);
    BusinessSegmentDao findById(Integer id);
    List<BusinessSegmentDao> findAllByIds(List<Integer> ids);
    List<BusinessSegmentDao> findAll();
}
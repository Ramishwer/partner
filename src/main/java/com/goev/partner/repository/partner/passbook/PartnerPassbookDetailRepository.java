package com.goev.partner.repository.partner.passbook;

import com.goev.partner.dao.partner.passbook.PartnerPassbookDetailDao;

import java.util.List;

public interface PartnerPassbookDetailRepository {
    PartnerPassbookDetailDao save(PartnerPassbookDetailDao partner);
    PartnerPassbookDetailDao update(PartnerPassbookDetailDao partner);
    void delete(Integer id);
    PartnerPassbookDetailDao findByUUID(String uuid);
    PartnerPassbookDetailDao findById(Integer id);
    List<PartnerPassbookDetailDao> findAllByIds(List<Integer> ids);
    List<PartnerPassbookDetailDao> findAll();
}
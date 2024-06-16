package com.goev.partner.repository.partner.app;

import com.goev.partner.dao.partner.app.PartnerAppEventDao;

import java.util.List;

public interface PartnerAppEventRepository {
    PartnerAppEventDao save(PartnerAppEventDao partner);

    PartnerAppEventDao update(PartnerAppEventDao partner);

    void delete(Integer id);

    PartnerAppEventDao findByUUID(String uuid);

    PartnerAppEventDao findById(Integer id);

    List<PartnerAppEventDao> findAllByIds(List<Integer> ids);

    List<PartnerAppEventDao> findAllActive();
}
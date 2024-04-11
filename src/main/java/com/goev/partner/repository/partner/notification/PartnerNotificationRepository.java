package com.goev.partner.repository.partner.notification;

import com.goev.partner.dao.partner.notification.PartnerNotificationDao;

import java.util.List;

public interface PartnerNotificationRepository {
    PartnerNotificationDao save(PartnerNotificationDao partner);
    PartnerNotificationDao update(PartnerNotificationDao partner);
    void delete(Integer id);
    PartnerNotificationDao findByUUID(String uuid);
    PartnerNotificationDao findById(Integer id);
    List<PartnerNotificationDao> findAllByIds(List<Integer> ids);
    List<PartnerNotificationDao> findAll();
}
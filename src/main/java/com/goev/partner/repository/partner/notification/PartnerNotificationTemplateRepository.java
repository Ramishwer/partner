package com.goev.partner.repository.partner.notification;

import com.goev.partner.dao.partner.notification.PartnerNotificationTemplateDao;

import java.util.List;

public interface PartnerNotificationTemplateRepository {
    PartnerNotificationTemplateDao save(PartnerNotificationTemplateDao partner);
    PartnerNotificationTemplateDao update(PartnerNotificationTemplateDao partner);
    void delete(Integer id);
    PartnerNotificationTemplateDao findByUUID(String uuid);
    PartnerNotificationTemplateDao findById(Integer id);
    List<PartnerNotificationTemplateDao> findAllByIds(List<Integer> ids);
    List<PartnerNotificationTemplateDao> findAll();
}
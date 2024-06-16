package com.goev.partner.repository.partner.app;

import com.goev.partner.dao.partner.app.PartnerAppSupportedLanguageDao;

import java.util.List;

public interface PartnerAppSupportedLanguageRepository {
    PartnerAppSupportedLanguageDao save(PartnerAppSupportedLanguageDao partner);

    PartnerAppSupportedLanguageDao update(PartnerAppSupportedLanguageDao partner);

    void delete(Integer id);

    PartnerAppSupportedLanguageDao findByUUID(String uuid);

    PartnerAppSupportedLanguageDao findById(Integer id);

    List<PartnerAppSupportedLanguageDao> findAllByIds(List<Integer> ids);

    List<PartnerAppSupportedLanguageDao> findAllActive();
}
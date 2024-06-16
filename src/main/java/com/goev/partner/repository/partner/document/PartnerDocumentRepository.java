package com.goev.partner.repository.partner.document;

import com.goev.partner.dao.partner.document.PartnerDocumentDao;

import java.util.List;
import java.util.Map;

public interface PartnerDocumentRepository {
    PartnerDocumentDao save(PartnerDocumentDao partner);

    PartnerDocumentDao update(PartnerDocumentDao partner);

    void delete(Integer id);

    PartnerDocumentDao findByUUID(String uuid);

    PartnerDocumentDao findById(Integer id);

    List<PartnerDocumentDao> findAllByIds(List<Integer> ids);

    List<PartnerDocumentDao> findAllByPartnerIdAndDocumentTypeIds(Integer partnerId, List<Integer> ids);

    List<PartnerDocumentDao> findAllByPartnerId(Integer partnerId);

    Map<Integer, PartnerDocumentDao> getExistingDocumentMap(Integer partnerId, List<Integer> activeDocumentTypeIds);
}
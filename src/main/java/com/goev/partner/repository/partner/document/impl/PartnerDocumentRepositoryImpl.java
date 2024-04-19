package com.goev.partner.repository.partner.document.impl;

import com.goev.partner.dao.partner.document.PartnerDocumentDao;
import com.goev.partner.repository.partner.document.PartnerDocumentRepository;
import com.goev.lib.enums.RecordState;
import com.goev.record.partner.tables.records.PartnerDocumentsRecord;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.goev.record.partner.tables.PartnerDocuments.PARTNER_DOCUMENTS;

@Repository
@AllArgsConstructor
@Slf4j
public class PartnerDocumentRepositoryImpl implements PartnerDocumentRepository {
    private final DSLContext context;

    @Override
    public PartnerDocumentDao save(PartnerDocumentDao partnerDocument) {
        PartnerDocumentsRecord partnersDocumentRecord = context.newRecord(PARTNER_DOCUMENTS, partnerDocument);
        partnersDocumentRecord.store();
        partnerDocument.setId(partnersDocumentRecord.getId());
        partnerDocument.setUuid(partnerDocument.getUuid());
        return partnerDocument;
    }

    @Override
    public PartnerDocumentDao update(PartnerDocumentDao partnerDocument) {
        PartnerDocumentsRecord partnersDocumentRecord = context.newRecord(PARTNER_DOCUMENTS, partnerDocument);
        partnersDocumentRecord.update();
        return partnerDocument;
    }

    @Override
    public void delete(Integer id) {
        context.update(PARTNER_DOCUMENTS).set(PARTNER_DOCUMENTS.STATE, RecordState.DELETED.name()).where(PARTNER_DOCUMENTS.ID.eq(id)).execute();
    }

    @Override
    public PartnerDocumentDao findByUUID(String uuid) {
        return context.selectFrom(PARTNER_DOCUMENTS).where(PARTNER_DOCUMENTS.UUID.eq(uuid)).fetchAnyInto(PartnerDocumentDao.class);
    }

    @Override
    public PartnerDocumentDao findById(Integer id) {
        return context.selectFrom(PARTNER_DOCUMENTS).where(PARTNER_DOCUMENTS.ID.eq(id)).fetchAnyInto(PartnerDocumentDao.class);
    }

    @Override
    public List<PartnerDocumentDao> findAllByIds(List<Integer> ids) {
        return context.selectFrom(PARTNER_DOCUMENTS).where(PARTNER_DOCUMENTS.ID.in(ids)).fetchInto(PartnerDocumentDao.class);
    }

    @Override
    public List<PartnerDocumentDao> findAllByPartnerId(Integer partnerId) {
        return context.selectFrom(PARTNER_DOCUMENTS).where(PARTNER_DOCUMENTS.PARTNER_ID.eq(partnerId))
                .and(PARTNER_DOCUMENTS.STATE.eq(RecordState.ACTIVE.name()))
                .fetchInto(PartnerDocumentDao.class);
    }

    @Override
    public List<PartnerDocumentDao> findAllByPartnerIdAndDocumentTypeIds(Integer partnerId, List<Integer> ids) {
        return context.selectFrom(PARTNER_DOCUMENTS).where(PARTNER_DOCUMENTS.PARTNER_ID.eq(partnerId))
                .and(PARTNER_DOCUMENTS.STATE.eq(RecordState.ACTIVE.name()))
                .and(PARTNER_DOCUMENTS.PARTNER_DOCUMENT_TYPE_ID.in(ids))
                .fetchInto(PartnerDocumentDao.class);
    }

    @Override
    public Map<Integer, PartnerDocumentDao> getExistingDocumentMap(Integer partnerId, List<Integer> activeDocumentTypeIds) {
        List<PartnerDocumentDao> existingDocuments = findAllByPartnerIdAndDocumentTypeIds(partnerId, activeDocumentTypeIds);
        Map<Integer, PartnerDocumentDao> documentMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(existingDocuments)) {
            for (PartnerDocumentDao document : existingDocuments) {
                if (activeDocumentTypeIds.contains(document.getId())) {
                    documentMap.put(document.getPartnerDocumentTypeId(), document);
                }
            }
        }
        return documentMap;
    }

}
package com.goev.partner.repository.partner.detail.impl;

import com.goev.partner.dao.partner.detail.PartnerAttributeDao;
import com.goev.partner.repository.partner.detail.PartnerAttributeRepository;
import com.goev.lib.enums.RecordState;
import com.goev.record.partner.tables.records.PartnerAttributesRecord;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.goev.record.partner.tables.PartnerAttributes.PARTNER_ATTRIBUTES;

@Slf4j
@Repository
@AllArgsConstructor
public class PartnerAttributeRepositoryImpl implements PartnerAttributeRepository {

    private final DSLContext context;

    @Override
    public PartnerAttributeDao save(PartnerAttributeDao attribute) {
        PartnerAttributesRecord partnerAttributesRecord = context.newRecord(PARTNER_ATTRIBUTES, attribute);
        partnerAttributesRecord.store();
        attribute.setId(partnerAttributesRecord.getId());
        attribute.setUuid(partnerAttributesRecord.getUuid());
        return attribute;
    }

    @Override
    public PartnerAttributeDao update(PartnerAttributeDao attribute) {
        PartnerAttributesRecord partnerAttributesRecord = context.newRecord(PARTNER_ATTRIBUTES, attribute);
        partnerAttributesRecord.update();
        return attribute;
    }

    @Override
    public void delete(Integer id) {
        context.update(PARTNER_ATTRIBUTES).set(PARTNER_ATTRIBUTES.STATE, RecordState.DELETED.name()).where(PARTNER_ATTRIBUTES.ID.eq(id)).execute();
    }

    @Override
    public PartnerAttributeDao findByUUID(String uuid) {
        return context.selectFrom(PARTNER_ATTRIBUTES).where(PARTNER_ATTRIBUTES.UUID.eq(uuid)).fetchAnyInto(PartnerAttributeDao.class);
    }

    @Override
    public PartnerAttributeDao findById(Integer id) {
        return context.selectFrom(PARTNER_ATTRIBUTES).where(PARTNER_ATTRIBUTES.ID.eq(id)).fetchAnyInto(PartnerAttributeDao.class);
    }

    @Override
    public List<PartnerAttributeDao> findAllByIds(List<Integer> ids) {
        return context.selectFrom(PARTNER_ATTRIBUTES).where(PARTNER_ATTRIBUTES.ID.in(ids)).fetchInto(PartnerAttributeDao.class);
    }

    @Override
    public List<PartnerAttributeDao> findAll() {
        return context.selectFrom(PARTNER_ATTRIBUTES).fetchInto(PartnerAttributeDao.class);
    }
}

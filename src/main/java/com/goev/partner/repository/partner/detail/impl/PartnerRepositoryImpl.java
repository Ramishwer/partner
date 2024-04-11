package com.goev.partner.repository.partner.detail.impl;

import com.goev.partner.dao.partner.detail.PartnerDao;
import com.goev.partner.repository.partner.detail.PartnerRepository;
import com.goev.lib.enums.RecordState;
import com.goev.record.partner.tables.records.PartnersRecord;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.goev.record.partner.tables.Partners.PARTNERS;

@Slf4j
@Repository
@AllArgsConstructor
public class PartnerRepositoryImpl implements PartnerRepository {

    private final DSLContext context;

    @Override
    public PartnerDao save(PartnerDao partner) {
        PartnersRecord partnersRecord = context.newRecord(PARTNERS, partner);
        partnersRecord.store();
        partner.setId(partnersRecord.getId());
        partner.setUuid(partnersRecord.getUuid());
        return partner;
    }

    @Override
    public PartnerDao update(PartnerDao partner) {
        PartnersRecord partnersRecord = context.newRecord(PARTNERS, partner);
        partnersRecord.update();
        return partner;
    }

    @Override
    public void delete(Integer id) {
        context.update(PARTNERS).set(PARTNERS.STATE, RecordState.DELETED.name()).where(PARTNERS.ID.eq(id)).execute();
    }

    @Override
    public PartnerDao findByUUID(String uuid) {
        return context.selectFrom(PARTNERS).where(PARTNERS.UUID.eq(uuid)).fetchAnyInto(PartnerDao.class);
    }

    @Override
    public PartnerDao findById(Integer id) {
        return context.selectFrom(PARTNERS).where(PARTNERS.ID.eq(id)).fetchAnyInto(PartnerDao.class);
    }

    @Override
    public List<PartnerDao> findAllByIds(List<Integer> ids) {
        return context.selectFrom(PARTNERS).where(PARTNERS.ID.in(ids)).fetchInto(PartnerDao.class);
    }

    @Override
    public List<PartnerDao> findAll() {
        return context.selectFrom(PARTNERS).fetchInto(PartnerDao.class);
    }

    @Override
    public PartnerDao findByPhoneNumber(String phoneNumber) {
        return context.selectFrom(PARTNERS).where(PARTNERS.PHONE_NUMBER.eq(phoneNumber)).fetchAnyInto(PartnerDao.class);
    }

    @Override
    public PartnerDao findByAuthUUID(String authUUID) {
        return context.selectFrom(PARTNERS).where(PARTNERS.AUTH_UUID.eq(authUUID)).fetchAnyInto(PartnerDao.class);
    }
}

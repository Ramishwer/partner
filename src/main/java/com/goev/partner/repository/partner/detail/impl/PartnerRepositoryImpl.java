package com.goev.partner.repository.partner.detail.impl;

import com.goev.lib.enums.RecordState;
import com.goev.partner.dao.partner.detail.PartnerDao;
import com.goev.partner.repository.partner.detail.PartnerRepository;
import com.goev.partner.utilities.EventExecutorUtils;
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
    private final EventExecutorUtils eventExecutor;

    @Override
    public PartnerDao save(PartnerDao partner) {
        PartnersRecord partnersRecord = context.newRecord(PARTNERS, partner);
        partnersRecord.store();
        partner.setId(partnersRecord.getId());
        partner.setUuid(partnersRecord.getUuid());
        partner.setCreatedBy(partnersRecord.getCreatedBy());
        partner.setUpdatedBy(partnersRecord.getUpdatedBy());
        partner.setCreatedOn(partnersRecord.getCreatedOn());
        partner.setUpdatedOn(partnersRecord.getUpdatedOn());
        partner.setIsActive(partnersRecord.getIsActive());
        partner.setState(partnersRecord.getState());
        partner.setApiSource(partnersRecord.getApiSource());
        partner.setNotes(partnersRecord.getNotes());
        return partner;
    }

    @Override
    public PartnerDao update(PartnerDao partner) {
        PartnersRecord partnersRecord = context.newRecord(PARTNERS, partner);
        partnersRecord.update();


        partner.setCreatedBy(partnersRecord.getCreatedBy());
        partner.setUpdatedBy(partnersRecord.getUpdatedBy());
        partner.setCreatedOn(partnersRecord.getCreatedOn());
        partner.setUpdatedOn(partnersRecord.getUpdatedOn());
        partner.setIsActive(partnersRecord.getIsActive());
        partner.setState(partnersRecord.getState());
        partner.setApiSource(partnersRecord.getApiSource());
        partner.setNotes(partnersRecord.getNotes());

        eventExecutor.fireEvent("PartnerUpdateEvent", partner);
        return partner;
    }

    @Override
    public void delete(Integer id) {
        context.update(PARTNERS)
                .set(PARTNERS.STATE, RecordState.DELETED.name())
                .where(PARTNERS.ID.eq(id))
                .and(PARTNERS.STATE.eq(RecordState.ACTIVE.name()))
                .and(PARTNERS.IS_ACTIVE.eq(true))
                .execute();
    }

    @Override
    public PartnerDao findByUUID(String uuid) {
        return context.selectFrom(PARTNERS).where(PARTNERS.UUID.eq(uuid))
                .and(PARTNERS.IS_ACTIVE.eq(true))
                .fetchAnyInto(PartnerDao.class);
    }

    @Override
    public PartnerDao findById(Integer id) {
        return context.selectFrom(PARTNERS).where(PARTNERS.ID.eq(id))
                .and(PARTNERS.IS_ACTIVE.eq(true))
                .fetchAnyInto(PartnerDao.class);
    }

    @Override
    public List<PartnerDao> findAllByIds(List<Integer> ids) {
        return context.selectFrom(PARTNERS).where(PARTNERS.ID.in(ids)).fetchInto(PartnerDao.class);
    }

    @Override
    public List<PartnerDao> findAllActive() {
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

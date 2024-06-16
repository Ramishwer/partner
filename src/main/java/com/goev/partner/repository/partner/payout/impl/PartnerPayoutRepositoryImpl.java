package com.goev.partner.repository.partner.payout.impl;

import com.goev.lib.enums.RecordState;
import com.goev.partner.dao.partner.payout.PartnerPayoutDao;
import com.goev.partner.dto.common.PageDto;
import com.goev.partner.enums.payout.PartnerPayoutStatus;
import com.goev.partner.repository.partner.payout.PartnerPayoutRepository;
import com.goev.record.partner.tables.records.PartnerPayoutsRecord;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.goev.record.partner.tables.PartnerPayouts.PARTNER_PAYOUTS;

@Slf4j
@Repository
@AllArgsConstructor
public class PartnerPayoutRepositoryImpl implements PartnerPayoutRepository {

    private final DSLContext context;

    @Override
    public PartnerPayoutDao save(PartnerPayoutDao partnerPayout) {
        PartnerPayoutsRecord partnerPayoutsRecord = context.newRecord(PARTNER_PAYOUTS, partnerPayout);
        partnerPayoutsRecord.store();
        partnerPayout.setId(partnerPayoutsRecord.getId());
        partnerPayout.setUuid(partnerPayout.getUuid());
        partnerPayout.setCreatedBy(partnerPayout.getCreatedBy());
        partnerPayout.setUpdatedBy(partnerPayout.getUpdatedBy());
        partnerPayout.setCreatedOn(partnerPayout.getCreatedOn());
        partnerPayout.setUpdatedOn(partnerPayout.getUpdatedOn());
        partnerPayout.setIsActive(partnerPayout.getIsActive());
        partnerPayout.setState(partnerPayout.getState());
        partnerPayout.setApiSource(partnerPayout.getApiSource());
        partnerPayout.setNotes(partnerPayout.getNotes());
        return partnerPayout;
    }

    @Override
    public PartnerPayoutDao update(PartnerPayoutDao partnerPayout) {
        PartnerPayoutsRecord partnerPayoutsRecord = context.newRecord(PARTNER_PAYOUTS, partnerPayout);
        partnerPayoutsRecord.update();


        partnerPayout.setCreatedBy(partnerPayoutsRecord.getCreatedBy());
        partnerPayout.setUpdatedBy(partnerPayoutsRecord.getUpdatedBy());
        partnerPayout.setCreatedOn(partnerPayoutsRecord.getCreatedOn());
        partnerPayout.setUpdatedOn(partnerPayoutsRecord.getUpdatedOn());
        partnerPayout.setIsActive(partnerPayoutsRecord.getIsActive());
        partnerPayout.setState(partnerPayoutsRecord.getState());
        partnerPayout.setApiSource(partnerPayoutsRecord.getApiSource());
        partnerPayout.setNotes(partnerPayoutsRecord.getNotes());
        return partnerPayout;
    }

    @Override
    public void delete(Integer id) {
     context.update(PARTNER_PAYOUTS)
     .set(PARTNER_PAYOUTS.STATE,RecordState.DELETED.name())
     .where(PARTNER_PAYOUTS.ID.eq(id))
     .and(PARTNER_PAYOUTS.STATE.eq(RecordState.ACTIVE.name()))
     .and(PARTNER_PAYOUTS.IS_ACTIVE.eq(true))
     .execute();
    } 

    @Override
    public PartnerPayoutDao findByUUID(String uuid) {
        return context.selectFrom(PARTNER_PAYOUTS).where(PARTNER_PAYOUTS.UUID.eq(uuid))
                .and(PARTNER_PAYOUTS.IS_ACTIVE.eq(true))
                .fetchAnyInto(PartnerPayoutDao.class);
    }

    @Override
    public PartnerPayoutDao findById(Integer id) {
        return context.selectFrom(PARTNER_PAYOUTS).where(PARTNER_PAYOUTS.ID.eq(id))
                .and(PARTNER_PAYOUTS.IS_ACTIVE.eq(true))
                .fetchAnyInto(PartnerPayoutDao.class);
    }

    @Override
    public List<PartnerPayoutDao> findAllByIds(List<Integer> ids) {
        return context.selectFrom(PARTNER_PAYOUTS).where(PARTNER_PAYOUTS.ID.in(ids)).fetchInto(PartnerPayoutDao.class);
    }

    @Override
    public List<PartnerPayoutDao> findAllActive() {
        return context.selectFrom(PARTNER_PAYOUTS).fetchInto(PartnerPayoutDao.class);
    }

    @Override
    public List<PartnerPayoutDao> findAllByPartnerId(Integer id, PageDto page) {
        return context.selectFrom(PARTNER_PAYOUTS)
                .where(PARTNER_PAYOUTS.PARTNER_ID.eq(id))
                .and(PARTNER_PAYOUTS.STATUS.in(PartnerPayoutStatus.COMPLETED.name(), PartnerPayoutStatus.PENDING.name()))
                .orderBy(PARTNER_PAYOUTS.FINALIZATION_DATE.desc(), PARTNER_PAYOUTS.ID.asc()).offset(page.getStart()).limit(page.getLimit())
                .fetchInto(PartnerPayoutDao.class);
    }
}

package com.goev.partner.repository.partner.payout.impl;

import com.goev.partner.dao.partner.payout.PartnerPayoutDao;
import com.goev.partner.dto.partner.payout.PartnerPayoutDto;
import com.goev.partner.repository.partner.payout.PartnerPayoutRepository;
import com.goev.lib.enums.RecordState;
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
        return partnerPayout;
    }

    @Override
    public PartnerPayoutDao update(PartnerPayoutDao partnerPayout) {
        PartnerPayoutsRecord partnerPayoutsRecord = context.newRecord(PARTNER_PAYOUTS, partnerPayout);
        partnerPayoutsRecord.update();
        return partnerPayout;
    }

    @Override
    public void delete(Integer id) {
        context.update(PARTNER_PAYOUTS).set(PARTNER_PAYOUTS.STATE, RecordState.DELETED.name()).where(PARTNER_PAYOUTS.ID.eq(id)).execute();
    }

    @Override
    public PartnerPayoutDao findByUUID(String uuid) {
        return context.selectFrom(PARTNER_PAYOUTS).where(PARTNER_PAYOUTS.UUID.eq(uuid)).fetchAnyInto(PartnerPayoutDao.class);
    }

    @Override
    public PartnerPayoutDao findById(Integer id) {
        return context.selectFrom(PARTNER_PAYOUTS).where(PARTNER_PAYOUTS.ID.eq(id)).fetchAnyInto(PartnerPayoutDao.class);
    }

    @Override
    public List<PartnerPayoutDao> findAllByIds(List<Integer> ids) {
        return context.selectFrom(PARTNER_PAYOUTS).where(PARTNER_PAYOUTS.ID.in(ids)).fetchInto(PartnerPayoutDao.class);
    }

    @Override
    public List<PartnerPayoutDao> findAll() {
        return context.selectFrom(PARTNER_PAYOUTS).fetchInto(PartnerPayoutDao.class);
    }

    @Override
    public List<PartnerPayoutDao> findAllByPartnerId(Integer id) {
        return context.selectFrom(PARTNER_PAYOUTS).where(PARTNER_PAYOUTS.PARTNER_ID.eq(id)).fetchInto(PartnerPayoutDao.class);
    }
}

package com.goev.partner.repository.partner.duty.impl;

import com.goev.partner.dao.partner.duty.PartnerShiftDao;
import com.goev.partner.repository.partner.duty.PartnerShiftRepository;
import com.goev.lib.enums.RecordState;
import com.goev.record.partner.tables.records.PartnerShiftsRecord;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.goev.record.partner.tables.PartnerShifts.PARTNER_SHIFTS;

@Slf4j
@Repository
@AllArgsConstructor
public class PartnerShiftRepositoryImpl implements PartnerShiftRepository {

    private final DSLContext context;

    @Override
    public PartnerShiftDao save(PartnerShiftDao partnerShiftDao) {
        PartnerShiftsRecord partnerShiftsRecord = context.newRecord(PARTNER_SHIFTS, partnerShiftDao);
        partnerShiftsRecord.store();
        partnerShiftDao.setId(partnerShiftsRecord.getId());
        partnerShiftDao.setUuid(partnerShiftsRecord.getUuid());
        return partnerShiftDao;
    }

    @Override
    public PartnerShiftDao update(PartnerShiftDao partnerShiftDao) {
        PartnerShiftsRecord partnerShiftsRecord = context.newRecord(PARTNER_SHIFTS, partnerShiftDao);
        partnerShiftsRecord.update();
        return partnerShiftDao;
    }

    @Override
    public void delete(Integer id) {
        context.update(PARTNER_SHIFTS).set(PARTNER_SHIFTS.STATE, RecordState.DELETED.name()).where(PARTNER_SHIFTS.ID.eq(id)).execute();
    }

    @Override
    public PartnerShiftDao findByUUID(String uuid) {
        return context.selectFrom(PARTNER_SHIFTS).where(PARTNER_SHIFTS.UUID.eq(uuid)).fetchAnyInto(PartnerShiftDao.class);
    }

    @Override
    public PartnerShiftDao findById(Integer id) {
        return context.selectFrom(PARTNER_SHIFTS).where(PARTNER_SHIFTS.ID.eq(id)).fetchAnyInto(PartnerShiftDao.class);
    }

    @Override
    public List<PartnerShiftDao> findAllByIds(List<Integer> ids) {
        return context.selectFrom(PARTNER_SHIFTS).where(PARTNER_SHIFTS.ID.in(ids)).fetchInto(PartnerShiftDao.class);
    }

    @Override
    public List<PartnerShiftDao> findAll() {
        return context.selectFrom(PARTNER_SHIFTS).fetchInto(PartnerShiftDao.class);
    }

    @Override
    public List<PartnerShiftDao> findAllByPartnerId(Integer id) {
        return context.selectFrom(PARTNER_SHIFTS).where(PARTNER_SHIFTS.PARTNER_ID.eq(id)).fetchInto(PartnerShiftDao.class);
    }
}

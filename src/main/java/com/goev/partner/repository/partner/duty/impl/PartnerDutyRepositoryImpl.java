package com.goev.partner.repository.partner.duty.impl;

import com.goev.lib.enums.RecordState;
import com.goev.partner.dao.partner.duty.PartnerDutyDao;
import com.goev.partner.dto.common.PageDto;
import com.goev.partner.repository.partner.duty.PartnerDutyRepository;
import com.goev.record.partner.tables.records.PartnerDutiesRecord;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.goev.record.partner.tables.PartnerDuties.PARTNER_DUTIES;

@Slf4j
@Repository
@AllArgsConstructor
public class PartnerDutyRepositoryImpl implements PartnerDutyRepository {

    private final DSLContext context;

    @Override
    public PartnerDutyDao save(PartnerDutyDao partnerDutyDao) {
        PartnerDutiesRecord partnerDutiesRecord = context.newRecord(PARTNER_DUTIES, partnerDutyDao);
        partnerDutiesRecord.store();
        partnerDutyDao.setId(partnerDutiesRecord.getId());
        partnerDutyDao.setUuid(partnerDutiesRecord.getUuid());
        partnerDutyDao.setCreatedBy(partnerDutiesRecord.getCreatedBy());
        partnerDutyDao.setUpdatedBy(partnerDutiesRecord.getUpdatedBy());
        partnerDutyDao.setCreatedOn(partnerDutiesRecord.getCreatedOn());
        partnerDutyDao.setUpdatedOn(partnerDutiesRecord.getUpdatedOn());
        partnerDutyDao.setIsActive(partnerDutiesRecord.getIsActive());
        partnerDutyDao.setState(partnerDutiesRecord.getState());
        partnerDutyDao.setApiSource(partnerDutiesRecord.getApiSource());
        partnerDutyDao.setNotes(partnerDutiesRecord.getNotes());
        return partnerDutyDao;
    }

    @Override
    public PartnerDutyDao update(PartnerDutyDao partnerDutyDao) {
        PartnerDutiesRecord partnerDutiesRecord = context.newRecord(PARTNER_DUTIES, partnerDutyDao);
        partnerDutiesRecord.update();


        partnerDutyDao.setCreatedBy(partnerDutiesRecord.getCreatedBy());
        partnerDutyDao.setUpdatedBy(partnerDutiesRecord.getUpdatedBy());
        partnerDutyDao.setCreatedOn(partnerDutiesRecord.getCreatedOn());
        partnerDutyDao.setUpdatedOn(partnerDutiesRecord.getUpdatedOn());
        partnerDutyDao.setIsActive(partnerDutiesRecord.getIsActive());
        partnerDutyDao.setState(partnerDutiesRecord.getState());
        partnerDutyDao.setApiSource(partnerDutiesRecord.getApiSource());
        partnerDutyDao.setNotes(partnerDutiesRecord.getNotes());
        return partnerDutyDao;
    }

    @Override
    public void delete(Integer id) {
        context.update(PARTNER_DUTIES)
                .set(PARTNER_DUTIES.STATE, RecordState.DELETED.name())
                .where(PARTNER_DUTIES.ID.eq(id))
                .and(PARTNER_DUTIES.STATE.eq(RecordState.ACTIVE.name()))
                .and(PARTNER_DUTIES.IS_ACTIVE.eq(true))
                .execute();
    }

    @Override
    public PartnerDutyDao findByUUID(String uuid) {
        return context.selectFrom(PARTNER_DUTIES).where(PARTNER_DUTIES.UUID.eq(uuid))
                .and(PARTNER_DUTIES.IS_ACTIVE.eq(true))
                .fetchAnyInto(PartnerDutyDao.class);
    }

    @Override
    public PartnerDutyDao findById(Integer id) {
        return context.selectFrom(PARTNER_DUTIES).where(PARTNER_DUTIES.ID.eq(id))
                .and(PARTNER_DUTIES.IS_ACTIVE.eq(true))
                .fetchAnyInto(PartnerDutyDao.class);
    }

    @Override
    public List<PartnerDutyDao> findAllByIds(List<Integer> ids) {
        return context.selectFrom(PARTNER_DUTIES).where(PARTNER_DUTIES.ID.in(ids)).fetchInto(PartnerDutyDao.class);
    }

    @Override
    public List<PartnerDutyDao> findAllActive() {
        return context.selectFrom(PARTNER_DUTIES).fetchInto(PartnerDutyDao.class);
    }


    @Override
    public List<PartnerDutyDao> findAllByPartnerId(Integer id, PageDto page) {
        return context.selectFrom(PARTNER_DUTIES).where(PARTNER_DUTIES.PARTNER_ID.eq(id)).fetchInto(PartnerDutyDao.class);
    }
}

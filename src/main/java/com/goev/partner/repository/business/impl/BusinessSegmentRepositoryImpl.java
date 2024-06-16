package com.goev.partner.repository.business.impl;

import com.goev.lib.enums.RecordState;
import com.goev.partner.dao.business.BusinessSegmentDao;
import com.goev.partner.repository.business.BusinessSegmentRepository;
import com.goev.record.partner.tables.records.BusinessSegmentsRecord;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.goev.record.partner.tables.BusinessSegments.BUSINESS_SEGMENTS;

@Slf4j
@Repository
@AllArgsConstructor
public class BusinessSegmentRepositoryImpl implements BusinessSegmentRepository {

    private final DSLContext context;

    @Override
    public BusinessSegmentDao save(BusinessSegmentDao segment) {
        BusinessSegmentsRecord businessSegmentsRecord = context.newRecord(BUSINESS_SEGMENTS, segment);
        businessSegmentsRecord.store();
        segment.setId(businessSegmentsRecord.getId());
        segment.setUuid(businessSegmentsRecord.getUuid());
        segment.setCreatedBy(businessSegmentsRecord.getCreatedBy());
        segment.setUpdatedBy(businessSegmentsRecord.getUpdatedBy());
        segment.setCreatedOn(businessSegmentsRecord.getCreatedOn());
        segment.setUpdatedOn(businessSegmentsRecord.getUpdatedOn());
        segment.setIsActive(businessSegmentsRecord.getIsActive());
        segment.setState(businessSegmentsRecord.getState());
        segment.setApiSource(businessSegmentsRecord.getApiSource());
        segment.setNotes(businessSegmentsRecord.getNotes());
        return segment;
    }

    @Override
    public BusinessSegmentDao update(BusinessSegmentDao segment) {
        BusinessSegmentsRecord businessSegmentsRecord = context.newRecord(BUSINESS_SEGMENTS, segment);
        businessSegmentsRecord.update();


        segment.setCreatedBy(businessSegmentsRecord.getCreatedBy());
        segment.setUpdatedBy(businessSegmentsRecord.getUpdatedBy());
        segment.setCreatedOn(businessSegmentsRecord.getCreatedOn());
        segment.setUpdatedOn(businessSegmentsRecord.getUpdatedOn());
        segment.setIsActive(businessSegmentsRecord.getIsActive());
        segment.setState(businessSegmentsRecord.getState());
        segment.setApiSource(businessSegmentsRecord.getApiSource());
        segment.setNotes(businessSegmentsRecord.getNotes());
        return segment;
    }

    @Override
    public void delete(Integer id) {
        context.update(BUSINESS_SEGMENTS)
                .set(BUSINESS_SEGMENTS.STATE, RecordState.DELETED.name())
                .where(BUSINESS_SEGMENTS.ID.eq(id))
                .and(BUSINESS_SEGMENTS.STATE.eq(RecordState.ACTIVE.name()))
                .and(BUSINESS_SEGMENTS.IS_ACTIVE.eq(true))
                .execute();
    }

    @Override
    public BusinessSegmentDao findByUUID(String uuid) {
        return context.selectFrom(BUSINESS_SEGMENTS).where(BUSINESS_SEGMENTS.UUID.eq(uuid))
                .and(BUSINESS_SEGMENTS.IS_ACTIVE.eq(true))
                .fetchAnyInto(BusinessSegmentDao.class);
    }

    @Override
    public BusinessSegmentDao findById(Integer id) {
        return context.selectFrom(BUSINESS_SEGMENTS).where(BUSINESS_SEGMENTS.ID.eq(id))
                .and(BUSINESS_SEGMENTS.IS_ACTIVE.eq(true))
                .fetchAnyInto(BusinessSegmentDao.class);
    }

    @Override
    public List<BusinessSegmentDao> findAllByIds(List<Integer> ids) {
        return context.selectFrom(BUSINESS_SEGMENTS).where(BUSINESS_SEGMENTS.ID.in(ids)).fetchInto(BusinessSegmentDao.class);
    }

    @Override
    public List<BusinessSegmentDao> findAllActive() {
        return context.selectFrom(BUSINESS_SEGMENTS).fetchInto(BusinessSegmentDao.class);
    }
}

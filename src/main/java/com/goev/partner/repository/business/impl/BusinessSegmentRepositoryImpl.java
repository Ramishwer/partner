package com.goev.partner.repository.business.impl;

import com.goev.partner.dao.business.BusinessSegmentDao;
import com.goev.partner.repository.business.BusinessSegmentRepository;
import com.goev.lib.enums.RecordState;
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
        return segment;
    }

    @Override
    public BusinessSegmentDao update(BusinessSegmentDao segment) {
        BusinessSegmentsRecord businessSegmentsRecord = context.newRecord(BUSINESS_SEGMENTS, segment);
        businessSegmentsRecord.update();
        return segment;
    }

    @Override
    public void delete(Integer id) {
        context.update(BUSINESS_SEGMENTS).set(BUSINESS_SEGMENTS.STATE, RecordState.DELETED.name()).where(BUSINESS_SEGMENTS.ID.eq(id)).execute();
    }

    @Override
    public BusinessSegmentDao findByUUID(String uuid) {
        return context.selectFrom(BUSINESS_SEGMENTS).where(BUSINESS_SEGMENTS.UUID.eq(uuid)).fetchAnyInto(BusinessSegmentDao.class);
    }

    @Override
    public BusinessSegmentDao findById(Integer id) {
        return context.selectFrom(BUSINESS_SEGMENTS).where(BUSINESS_SEGMENTS.ID.eq(id)).fetchAnyInto(BusinessSegmentDao.class);
    }

    @Override
    public List<BusinessSegmentDao> findAllByIds(List<Integer> ids) {
        return context.selectFrom(BUSINESS_SEGMENTS).where(BUSINESS_SEGMENTS.ID.in(ids)).fetchInto(BusinessSegmentDao.class);
    }

    @Override
    public List<BusinessSegmentDao> findAll() {
        return context.selectFrom(BUSINESS_SEGMENTS).fetchInto(BusinessSegmentDao.class);
    }
}

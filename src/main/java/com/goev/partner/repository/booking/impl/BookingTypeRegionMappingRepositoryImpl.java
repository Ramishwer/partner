package com.goev.partner.repository.booking.impl;


import com.goev.partner.dao.booking.BookingTypeRegionMappingDao;
import com.goev.partner.repository.booking.BookingTypeRegionMappingRepository;
import com.goev.lib.enums.RecordState;
import com.goev.record.partner.tables.records.BookingTypeRegionMappingsRecord;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.goev.record.partner.tables.BookingTypeRegionMappings.BOOKING_TYPE_REGION_MAPPINGS;

@Slf4j
@Repository
@AllArgsConstructor
public class BookingTypeRegionMappingRepositoryImpl implements BookingTypeRegionMappingRepository {

    private final DSLContext context;

    @Override
    public BookingTypeRegionMappingDao save(BookingTypeRegionMappingDao client) {
        BookingTypeRegionMappingsRecord bookingTypeRegionMappingsRecord = context.newRecord(BOOKING_TYPE_REGION_MAPPINGS, client);
        bookingTypeRegionMappingsRecord.store();
        client.setId(bookingTypeRegionMappingsRecord.getId());
        client.setUuid(bookingTypeRegionMappingsRecord.getUuid());
        return client;
    }

    @Override
    public BookingTypeRegionMappingDao update(BookingTypeRegionMappingDao client) {
        BookingTypeRegionMappingsRecord bookingTypeRegionMappingsRecord = context.newRecord(BOOKING_TYPE_REGION_MAPPINGS, client);
        bookingTypeRegionMappingsRecord.update();
        return client;
    }

    @Override
    public void delete(Integer id) {
        context.update(BOOKING_TYPE_REGION_MAPPINGS).set(BOOKING_TYPE_REGION_MAPPINGS.STATE, RecordState.DELETED.name()).where(BOOKING_TYPE_REGION_MAPPINGS.ID.eq(id)).execute();
    }

    @Override
    public BookingTypeRegionMappingDao findByUUID(String uuid) {
        return context.selectFrom(BOOKING_TYPE_REGION_MAPPINGS).where(BOOKING_TYPE_REGION_MAPPINGS.UUID.eq(uuid)).fetchAnyInto(BookingTypeRegionMappingDao.class);
    }

    @Override
    public BookingTypeRegionMappingDao findById(Integer id) {
        return context.selectFrom(BOOKING_TYPE_REGION_MAPPINGS).where(BOOKING_TYPE_REGION_MAPPINGS.ID.eq(id)).fetchAnyInto(BookingTypeRegionMappingDao.class);
    }

    @Override
    public List<BookingTypeRegionMappingDao> findAllByIds(List<Integer> ids) {
        return context.selectFrom(BOOKING_TYPE_REGION_MAPPINGS).where(BOOKING_TYPE_REGION_MAPPINGS.ID.in(ids)).fetchInto(BookingTypeRegionMappingDao.class);
    }

    @Override
    public List<BookingTypeRegionMappingDao> findAll() {
        return context.selectFrom(BOOKING_TYPE_REGION_MAPPINGS).fetchInto(BookingTypeRegionMappingDao.class);
    }
}

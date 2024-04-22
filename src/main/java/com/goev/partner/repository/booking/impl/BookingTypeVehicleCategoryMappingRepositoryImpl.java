package com.goev.partner.repository.booking.impl;


import com.goev.partner.dao.booking.BookingTypeVehicleCategoryMappingDao;
import com.goev.partner.repository.booking.BookingTypeVehicleCategoryMappingRepository;
import com.goev.lib.enums.RecordState;
import com.goev.record.partner.tables.records.BookingTypeVehicleCategoryMappingsRecord;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.goev.record.partner.tables.BookingTypeVehicleCategoryMappings.BOOKING_TYPE_VEHICLE_CATEGORY_MAPPINGS;

@Slf4j
@Repository
@AllArgsConstructor
public class BookingTypeVehicleCategoryMappingRepositoryImpl implements BookingTypeVehicleCategoryMappingRepository {

    private final DSLContext context;

    @Override
    public BookingTypeVehicleCategoryMappingDao save(BookingTypeVehicleCategoryMappingDao client) {
        BookingTypeVehicleCategoryMappingsRecord bookingTypeVehicleCategoryMappingsRecord = context.newRecord(BOOKING_TYPE_VEHICLE_CATEGORY_MAPPINGS, client);
        bookingTypeVehicleCategoryMappingsRecord.store();
        client.setId(bookingTypeVehicleCategoryMappingsRecord.getId());
        client.setUuid(bookingTypeVehicleCategoryMappingsRecord.getUuid());
        return client;
    }

    @Override
    public BookingTypeVehicleCategoryMappingDao update(BookingTypeVehicleCategoryMappingDao client) {
        BookingTypeVehicleCategoryMappingsRecord bookingTypeVehicleCategoryMappingsRecord = context.newRecord(BOOKING_TYPE_VEHICLE_CATEGORY_MAPPINGS, client);
        bookingTypeVehicleCategoryMappingsRecord.update();
        return client;
    }

    @Override
    public void delete(Integer id) {
        context.update(BOOKING_TYPE_VEHICLE_CATEGORY_MAPPINGS).set(BOOKING_TYPE_VEHICLE_CATEGORY_MAPPINGS.STATE, RecordState.DELETED.name()).where(BOOKING_TYPE_VEHICLE_CATEGORY_MAPPINGS.ID.eq(id)).execute();
    }

    @Override
    public BookingTypeVehicleCategoryMappingDao findByUUID(String uuid) {
        return context.selectFrom(BOOKING_TYPE_VEHICLE_CATEGORY_MAPPINGS).where(BOOKING_TYPE_VEHICLE_CATEGORY_MAPPINGS.UUID.eq(uuid)).fetchAnyInto(BookingTypeVehicleCategoryMappingDao.class);
    }

    @Override
    public BookingTypeVehicleCategoryMappingDao findById(Integer id) {
        return context.selectFrom(BOOKING_TYPE_VEHICLE_CATEGORY_MAPPINGS).where(BOOKING_TYPE_VEHICLE_CATEGORY_MAPPINGS.ID.eq(id)).fetchAnyInto(BookingTypeVehicleCategoryMappingDao.class);
    }

    @Override
    public List<BookingTypeVehicleCategoryMappingDao> findAllByIds(List<Integer> ids) {
        return context.selectFrom(BOOKING_TYPE_VEHICLE_CATEGORY_MAPPINGS).where(BOOKING_TYPE_VEHICLE_CATEGORY_MAPPINGS.ID.in(ids)).fetchInto(BookingTypeVehicleCategoryMappingDao.class);
    }

    @Override
    public List<BookingTypeVehicleCategoryMappingDao> findAll() {
        return context.selectFrom(BOOKING_TYPE_VEHICLE_CATEGORY_MAPPINGS).fetchInto(BookingTypeVehicleCategoryMappingDao.class);
    }
}

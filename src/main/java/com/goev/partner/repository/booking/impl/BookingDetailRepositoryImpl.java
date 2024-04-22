package com.goev.partner.repository.booking.impl;


import com.goev.partner.dao.booking.BookingDetailDao;
import com.goev.partner.repository.booking.BookingDetailRepository;
import com.goev.lib.enums.RecordState;
import com.goev.record.partner.tables.records.BookingDetailsRecord;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.goev.record.partner.tables.BookingDetails.BOOKING_DETAILS;

@Slf4j
@Repository
@AllArgsConstructor
public class BookingDetailRepositoryImpl implements BookingDetailRepository {

    private final DSLContext context;

    @Override
    public BookingDetailDao save(BookingDetailDao client) {
        BookingDetailsRecord bookingDetailsRecord = context.newRecord(BOOKING_DETAILS, client);
        bookingDetailsRecord.store();
        client.setId(bookingDetailsRecord.getId());
        client.setUuid(bookingDetailsRecord.getUuid());
        return client;
    }

    @Override
    public BookingDetailDao update(BookingDetailDao client) {
        BookingDetailsRecord bookingDetailsRecord = context.newRecord(BOOKING_DETAILS, client);
        bookingDetailsRecord.update();
        return client;
    }

    @Override
    public void delete(Integer id) {
        context.update(BOOKING_DETAILS).set(BOOKING_DETAILS.STATE, RecordState.DELETED.name()).where(BOOKING_DETAILS.ID.eq(id)).execute();
    }

    @Override
    public BookingDetailDao findByUUID(String uuid) {
        return context.selectFrom(BOOKING_DETAILS).where(BOOKING_DETAILS.UUID.eq(uuid)).fetchAnyInto(BookingDetailDao.class);
    }

    @Override
    public BookingDetailDao findById(Integer id) {
        return context.selectFrom(BOOKING_DETAILS).where(BOOKING_DETAILS.ID.eq(id)).fetchAnyInto(BookingDetailDao.class);
    }

    @Override
    public List<BookingDetailDao> findAllByIds(List<Integer> ids) {
        return context.selectFrom(BOOKING_DETAILS).where(BOOKING_DETAILS.ID.in(ids)).fetchInto(BookingDetailDao.class);
    }

    @Override
    public List<BookingDetailDao> findAll() {
        return context.selectFrom(BOOKING_DETAILS).fetchInto(BookingDetailDao.class);
    }
}

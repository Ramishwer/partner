package com.goev.partner.repository.booking.impl;


import com.goev.partner.dao.booking.BookingFeedbackDao;
import com.goev.partner.repository.booking.BookingFeedbackRepository;
import com.goev.lib.enums.RecordState;
import com.goev.record.partner.tables.records.BookingFeedbacksRecord;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.goev.record.partner.tables.BookingFeedbacks.BOOKING_FEEDBACKS;

@Slf4j
@Repository
@AllArgsConstructor
public class BookingFeedbackRepositoryImpl implements BookingFeedbackRepository {

    private final DSLContext context;

    @Override
    public BookingFeedbackDao save(BookingFeedbackDao client) {
        BookingFeedbacksRecord bookingFeedbacksRecord = context.newRecord(BOOKING_FEEDBACKS, client);
        bookingFeedbacksRecord.store();
        client.setId(bookingFeedbacksRecord.getId());
        client.setUuid(bookingFeedbacksRecord.getUuid());
        return client;
    }

    @Override
    public BookingFeedbackDao update(BookingFeedbackDao client) {
        BookingFeedbacksRecord bookingFeedbacksRecord = context.newRecord(BOOKING_FEEDBACKS, client);
        bookingFeedbacksRecord.update();
        return client;
    }

    @Override
    public void delete(Integer id) {
        context.update(BOOKING_FEEDBACKS).set(BOOKING_FEEDBACKS.STATE, RecordState.DELETED.name()).where(BOOKING_FEEDBACKS.ID.eq(id)).execute();
    }

    @Override
    public BookingFeedbackDao findByUUID(String uuid) {
        return context.selectFrom(BOOKING_FEEDBACKS).where(BOOKING_FEEDBACKS.UUID.eq(uuid)).fetchAnyInto(BookingFeedbackDao.class);
    }

    @Override
    public BookingFeedbackDao findById(Integer id) {
        return context.selectFrom(BOOKING_FEEDBACKS).where(BOOKING_FEEDBACKS.ID.eq(id)).fetchAnyInto(BookingFeedbackDao.class);
    }

    @Override
    public List<BookingFeedbackDao> findAllByIds(List<Integer> ids) {
        return context.selectFrom(BOOKING_FEEDBACKS).where(BOOKING_FEEDBACKS.ID.in(ids)).fetchInto(BookingFeedbackDao.class);
    }

    @Override
    public List<BookingFeedbackDao> findAll() {
        return context.selectFrom(BOOKING_FEEDBACKS).fetchInto(BookingFeedbackDao.class);
    }
}

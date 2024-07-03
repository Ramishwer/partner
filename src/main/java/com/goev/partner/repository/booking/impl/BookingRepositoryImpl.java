package com.goev.partner.repository.booking.impl;


import com.goev.lib.enums.RecordState;
import com.goev.partner.dao.booking.BookingDao;
import com.goev.partner.dto.common.PageDto;
import com.goev.partner.enums.booking.BookingStatus;
import com.goev.partner.repository.booking.BookingRepository;
import com.goev.partner.utilities.EventExecutorUtils;
import com.goev.partner.utilities.RequestContext;
import com.goev.record.partner.tables.records.BookingsRecord;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.goev.record.partner.tables.Bookings.BOOKINGS;

@Slf4j
@Repository
@AllArgsConstructor
public class BookingRepositoryImpl implements BookingRepository {

    private final DSLContext context;
    private final EventExecutorUtils eventExecutor;

    @Override
    public BookingDao save(BookingDao bookingDao) {
        BookingsRecord bookingsRecord = context.newRecord(BOOKINGS, bookingDao);
        bookingsRecord.store();
        bookingDao.setId(bookingsRecord.getId());
        bookingDao.setUuid(bookingsRecord.getUuid());
        bookingDao.setCreatedBy(bookingsRecord.getCreatedBy());
        bookingDao.setUpdatedBy(bookingsRecord.getUpdatedBy());
        bookingDao.setCreatedOn(bookingsRecord.getCreatedOn());
        bookingDao.setUpdatedOn(bookingsRecord.getUpdatedOn());
        bookingDao.setIsActive(bookingsRecord.getIsActive());
        bookingDao.setState(bookingsRecord.getState());
        bookingDao.setApiSource(bookingsRecord.getApiSource());
        bookingDao.setNotes(bookingsRecord.getNotes());
        return bookingDao;
    }

    @Override
    public BookingDao update(BookingDao bookingDao) {
        BookingsRecord bookingsRecord = context.newRecord(BOOKINGS, bookingDao);
        bookingsRecord.update();


        bookingDao.setCreatedBy(bookingsRecord.getCreatedBy());
        bookingDao.setUpdatedBy(bookingsRecord.getUpdatedBy());
        bookingDao.setCreatedOn(bookingsRecord.getCreatedOn());
        bookingDao.setUpdatedOn(bookingsRecord.getUpdatedOn());
        bookingDao.setIsActive(bookingsRecord.getIsActive());
        bookingDao.setState(bookingsRecord.getState());
        bookingDao.setApiSource(bookingsRecord.getApiSource());
        bookingDao.setNotes(bookingsRecord.getNotes());
        if("API".equals(RequestContext.getRequestSource()))
            eventExecutor.fireEvent("PartnerUpdateEvent", bookingDao);
        return bookingDao;
    }


    @Override
    public BookingDao findByUUID(String uuid) {
        return context.selectFrom(BOOKINGS)
                .where(BOOKINGS.UUID.eq(uuid))
                .and(BOOKINGS.STATE.eq(RecordState.ACTIVE.name()))
                .and(BOOKINGS.IS_ACTIVE.eq(true))
                .fetchAnyInto(BookingDao.class);
    }

    @Override
    public BookingDao findByPartnerIdAndUUID(Integer partnerId, String uuid) {
        return context.selectFrom(BOOKINGS)
                .where(BOOKINGS.UUID.eq(uuid))
                .and(BOOKINGS.PARTNER_ID.eq(partnerId))
                .and(BOOKINGS.STATE.eq(RecordState.ACTIVE.name()))
                .and(BOOKINGS.IS_ACTIVE.eq(true))
                .fetchAnyInto(BookingDao.class);
    }

    @Override
    public BookingDao findById(Integer id) {
        return context.selectFrom(BOOKINGS)
                .where(BOOKINGS.ID.eq(id))
                .and(BOOKINGS.STATE.eq(RecordState.ACTIVE.name()))
                .and(BOOKINGS.IS_ACTIVE.eq(true))
                .fetchAnyInto(BookingDao.class);
    }

    @Override
    public List<BookingDao> findAllByIds(List<Integer> ids) {
        return context.selectFrom(BOOKINGS)
                .where(BOOKINGS.ID.in(ids))
                .and(BOOKINGS.STATE.eq(RecordState.ACTIVE.name()))
                .and(BOOKINGS.IS_ACTIVE.eq(true))
                .fetchInto(BookingDao.class);
    }


    @Override
    public List<BookingDao> findAllByPartnerId(Integer partnerId, PageDto page) {
        return context.selectFrom(BOOKINGS)
                .where(BOOKINGS.PARTNER_ID.eq(partnerId))
                .and(BOOKINGS.STATUS.in(BookingStatus.COMPLETED.name()))
                .and(BOOKINGS.STATE.eq(RecordState.ACTIVE.name()))
                .and(BOOKINGS.IS_ACTIVE.eq(true))
                .orderBy(BOOKINGS.PLANNED_START_TIME.desc(), BOOKINGS.ID.asc())
                .offset(page.getStart())
                .limit(page.getLimit())
                .fetchInto(BookingDao.class);
    }

    @Override
    public List<BookingDao> findByPartnerIdAndStatus(Integer partnerId, String status) {
        return context.selectFrom(BOOKINGS)
                .where(BOOKINGS.PARTNER_ID.eq(partnerId))
                .and(BOOKINGS.STATUS.in(status))
                .and(BOOKINGS.STATE.eq(RecordState.ACTIVE.name()))
                .and(BOOKINGS.IS_ACTIVE.eq(true))
                .orderBy(BOOKINGS.PLANNED_START_TIME.asc(), BOOKINGS.ID.asc())
                .fetchInto(BookingDao.class);
    }
}

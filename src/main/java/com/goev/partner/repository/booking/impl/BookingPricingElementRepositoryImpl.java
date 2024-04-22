package com.goev.partner.repository.booking.impl;


import com.goev.partner.dao.booking.BookingPricingElementDao;
import com.goev.partner.repository.booking.BookingPricingElementRepository;
import com.goev.lib.enums.RecordState;
import com.goev.record.partner.tables.records.BookingPricingElementsRecord;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.goev.record.partner.tables.BookingPricingElements.BOOKING_PRICING_ELEMENTS;

@Slf4j
@Repository
@AllArgsConstructor
public class BookingPricingElementRepositoryImpl implements BookingPricingElementRepository {

    private final DSLContext context;

    @Override
    public BookingPricingElementDao save(BookingPricingElementDao client) {
        BookingPricingElementsRecord bookingPricingElementsRecord = context.newRecord(BOOKING_PRICING_ELEMENTS, client);
        bookingPricingElementsRecord.store();
        client.setId(bookingPricingElementsRecord.getId());
        client.setUuid(bookingPricingElementsRecord.getUuid());
        return client;
    }

    @Override
    public BookingPricingElementDao update(BookingPricingElementDao client) {
        BookingPricingElementsRecord bookingPricingElementsRecord = context.newRecord(BOOKING_PRICING_ELEMENTS, client);
        bookingPricingElementsRecord.update();
        return client;
    }

    @Override
    public void delete(Integer id) {
        context.update(BOOKING_PRICING_ELEMENTS).set(BOOKING_PRICING_ELEMENTS.STATE, RecordState.DELETED.name()).where(BOOKING_PRICING_ELEMENTS.ID.eq(id)).execute();
    }

    @Override
    public BookingPricingElementDao findByUUID(String uuid) {
        return context.selectFrom(BOOKING_PRICING_ELEMENTS).where(BOOKING_PRICING_ELEMENTS.UUID.eq(uuid)).fetchAnyInto(BookingPricingElementDao.class);
    }

    @Override
    public BookingPricingElementDao findById(Integer id) {
        return context.selectFrom(BOOKING_PRICING_ELEMENTS).where(BOOKING_PRICING_ELEMENTS.ID.eq(id)).fetchAnyInto(BookingPricingElementDao.class);
    }

    @Override
    public List<BookingPricingElementDao> findAllByIds(List<Integer> ids) {
        return context.selectFrom(BOOKING_PRICING_ELEMENTS).where(BOOKING_PRICING_ELEMENTS.ID.in(ids)).fetchInto(BookingPricingElementDao.class);
    }

    @Override
    public List<BookingPricingElementDao> findAll() {
        return context.selectFrom(BOOKING_PRICING_ELEMENTS).fetchInto(BookingPricingElementDao.class);
    }
}

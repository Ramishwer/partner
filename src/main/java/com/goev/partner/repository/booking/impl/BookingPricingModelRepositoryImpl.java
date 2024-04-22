package com.goev.partner.repository.booking.impl;


import com.goev.partner.dao.booking.BookingPricingModelDao;
import com.goev.partner.repository.booking.BookingPricingModelRepository;
import com.goev.lib.enums.RecordState;
import com.goev.record.partner.tables.records.BookingPricingModelsRecord;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.goev.record.partner.tables.BookingPricingModels.BOOKING_PRICING_MODELS;

@Slf4j
@Repository
@AllArgsConstructor
public class BookingPricingModelRepositoryImpl implements BookingPricingModelRepository {

    private final DSLContext context;

    @Override
    public BookingPricingModelDao save(BookingPricingModelDao client) {
        BookingPricingModelsRecord bookingPricingModelsRecord = context.newRecord(BOOKING_PRICING_MODELS, client);
        bookingPricingModelsRecord.store();
        client.setId(bookingPricingModelsRecord.getId());
        client.setUuid(bookingPricingModelsRecord.getUuid());
        return client;
    }

    @Override
    public BookingPricingModelDao update(BookingPricingModelDao client) {
        BookingPricingModelsRecord bookingPricingModelsRecord = context.newRecord(BOOKING_PRICING_MODELS, client);
        bookingPricingModelsRecord.update();
        return client;
    }

    @Override
    public void delete(Integer id) {
        context.update(BOOKING_PRICING_MODELS).set(BOOKING_PRICING_MODELS.STATE, RecordState.DELETED.name()).where(BOOKING_PRICING_MODELS.ID.eq(id)).execute();
    }

    @Override
    public BookingPricingModelDao findByUUID(String uuid) {
        return context.selectFrom(BOOKING_PRICING_MODELS).where(BOOKING_PRICING_MODELS.UUID.eq(uuid)).fetchAnyInto(BookingPricingModelDao.class);
    }

    @Override
    public BookingPricingModelDao findById(Integer id) {
        return context.selectFrom(BOOKING_PRICING_MODELS).where(BOOKING_PRICING_MODELS.ID.eq(id)).fetchAnyInto(BookingPricingModelDao.class);
    }

    @Override
    public List<BookingPricingModelDao> findAllByIds(List<Integer> ids) {
        return context.selectFrom(BOOKING_PRICING_MODELS).where(BOOKING_PRICING_MODELS.ID.in(ids)).fetchInto(BookingPricingModelDao.class);
    }

    @Override
    public List<BookingPricingModelDao> findAll() {
        return context.selectFrom(BOOKING_PRICING_MODELS).fetchInto(BookingPricingModelDao.class);
    }
}

package com.goev.partner.repository.booking;

import com.goev.partner.dao.booking.BookingPricingDetailDao;

import java.util.List;

public interface BookingPricingDetailRepository {
    BookingPricingDetailDao save(BookingPricingDetailDao pricingDetail);
    BookingPricingDetailDao update(BookingPricingDetailDao pricingDetail);
    void delete(Integer id);
    BookingPricingDetailDao findByUUID(String uuid);
    BookingPricingDetailDao findById(Integer id);
    List<BookingPricingDetailDao> findAllByIds(List<Integer> ids);
    List<BookingPricingDetailDao> findAll();
}
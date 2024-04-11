package com.goev.partner.repository.booking;

import com.goev.partner.dao.booking.BookingDao;

import java.util.List;

public interface BookingRepository {
    BookingDao save(BookingDao booking);
    BookingDao update(BookingDao booking);
    void delete(Integer id);
    BookingDao findByUUID(String uuid);
    BookingDao findById(Integer id);
    List<BookingDao> findAllByIds(List<Integer> ids);
    List<BookingDao> findAll();
}
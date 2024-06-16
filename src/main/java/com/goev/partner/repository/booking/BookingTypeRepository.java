package com.goev.partner.repository.booking;

import com.goev.partner.dao.booking.BookingTypeDao;

import java.util.List;

public interface BookingTypeRepository {
    BookingTypeDao save(BookingTypeDao type);

    BookingTypeDao update(BookingTypeDao type);

    void delete(Integer id);

    BookingTypeDao findByUUID(String uuid);

    BookingTypeDao findById(Integer id);

    List<BookingTypeDao> findAllByIds(List<Integer> ids);

    List<BookingTypeDao> findAllActive();
}

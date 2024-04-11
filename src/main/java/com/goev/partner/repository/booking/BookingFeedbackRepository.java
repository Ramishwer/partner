
package com.goev.partner.repository.booking;

import com.goev.partner.dao.booking.BookingFeedbackDao;

import java.util.List;

public interface BookingFeedbackRepository {
    BookingFeedbackDao save(BookingFeedbackDao feedback);
    BookingFeedbackDao update(BookingFeedbackDao feedback);
    void delete(Integer id);
    BookingFeedbackDao findByUUID(String uuid);
    BookingFeedbackDao findById(Integer id);
    List<BookingFeedbackDao> findAllByIds(List<Integer> ids);
    List<BookingFeedbackDao> findAll();
}
package com.goev.partner.repository.booking;

import com.goev.partner.dao.booking.BookingDao;
import com.goev.partner.dto.common.PageDto;

import java.util.List;

public interface BookingRepository {
    BookingDao save(BookingDao booking);
    BookingDao update(BookingDao booking);
    BookingDao findByUUID(String uuid);
    BookingDao findByPartnerIdAndUUID(Integer partnerId,String uuid);
    BookingDao findById(Integer id);
    List<BookingDao> findAllByIds(List<Integer> ids);
    List<BookingDao> findAllByPartnerId(Integer partnerId,  PageDto page);

}
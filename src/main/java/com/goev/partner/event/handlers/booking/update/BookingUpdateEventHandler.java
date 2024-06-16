package com.goev.partner.event.handlers.booking.update;

import com.goev.lib.event.core.Event;
import com.goev.lib.event.core.EventHandler;
import com.goev.partner.dao.booking.BookingDao;
import com.goev.partner.repository.booking.BookingRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class BookingUpdateEventHandler extends EventHandler<BookingDao> {

    private final BookingRepository bookingRepository;

    @Override
    public boolean onEvent(Event<BookingDao> event) {
        log.info("Data:{}", event.getData());
        BookingDao bookingDao = event.getData();
        if (bookingDao == null) {
            log.info("Booking Data Null");
            return false;
        }
        BookingDao existing = bookingRepository.findByUUID(bookingDao.getUuid());
        if (existing != null) {
            bookingDao.setId(existing.getId());
            bookingDao.setUuid(existing.getUuid());
            bookingDao.setCreatedBy(existing.getCreatedBy());
            bookingDao.setUpdatedBy(existing.getUpdatedBy());
            bookingDao.setCreatedOn(existing.getCreatedOn());
            bookingDao.setUpdatedOn(existing.getUpdatedOn());
            bookingDao.setIsActive(existing.getIsActive());
            bookingDao.setState(existing.getState());
            bookingDao.setApiSource(existing.getApiSource());
            bookingDao.setNotes(existing.getNotes());
            bookingRepository.update(bookingDao);
            return true;
        }
        return false;
    }
}
package com.goev.partner.event.handlers.booking.save;

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
public class BookingSaveEventHandler extends EventHandler<BookingDao> {

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
        if (existing == null) {
            bookingRepository.save(bookingDao);
            return true;
        }
        return false;
    }
}

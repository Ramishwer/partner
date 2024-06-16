package com.goev.partner.event.events.booking;

import com.goev.lib.event.core.Event;
import com.goev.partner.dao.booking.BookingDao;
import com.goev.partner.event.targets.CentralTarget;
import jakarta.annotation.PostConstruct;

public class BookingSaveEvent extends Event<BookingDao> {

    @Override
    @PostConstruct
    public void init() {
        registerEventTargets(CentralTarget.getTargetName());
    }

    @Override
    public String getName() {
        return "BookingSaveEvent";
    }

}

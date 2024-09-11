package com.goev.partner.event.events.booking;

import com.goev.partner.dao.booking.BookingDao;
import com.goev.partner.event.targets.CentralTarget;
import com.goev.lib.event.core.Event;
import com.goev.partner.event.targets.CustomerTarget;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class BookingUpdateEvent extends Event<BookingDao> {

    @Override
    @PostConstruct
    public void init() {

        registerEventTargets(CentralTarget.getTargetName());
        registerEventTargets(CustomerTarget.getTargetName());
    }

    @Override
    public String getName() {
        return "BookingUpdateEvent";
    }

}

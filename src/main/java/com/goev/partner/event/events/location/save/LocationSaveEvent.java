package com.goev.partner.event.events.location.save;

import com.goev.partner.dao.location.LocationDao;
import com.goev.partner.event.targets.PartnerTarget;
import com.goev.lib.event.core.Event;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class LocationSaveEvent extends Event<LocationDao> {

    @Override
    @PostConstruct
    public void init() {
        registerEventTargets(PartnerTarget.getTargetName());
    }

    @Override
    public String getName() {
        return "LocationSaveEvent";
    }

}

package com.goev.partner.event.events.location.update;

import com.goev.lib.event.core.Event;
import com.goev.partner.dao.location.LocationDao;
import com.goev.partner.event.targets.CentralTarget;
import jakarta.annotation.PostConstruct;

public class LocationUpdateEvent extends Event<LocationDao> {

    @Override
    @PostConstruct
    public void init() {
        registerEventTargets(CentralTarget.getTargetName());
    }

    @Override
    public String getName() {
        return "LocationUpdateEvent";
    }

}

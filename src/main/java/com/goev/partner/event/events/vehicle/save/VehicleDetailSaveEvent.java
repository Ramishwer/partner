package com.goev.partner.event.events.vehicle.save;

import com.goev.lib.event.core.Event;
import com.goev.partner.dao.vehicle.detail.VehicleDetailDao;
import com.goev.partner.event.targets.CentralTarget;
import jakarta.annotation.PostConstruct;

public class VehicleDetailSaveEvent extends Event<VehicleDetailDao> {

    @Override
    @PostConstruct
    public void init() {
        registerEventTargets(CentralTarget.getTargetName());
    }

    @Override
    public String getName() {
        return "VehicleDetailSaveEvent";
    }

}

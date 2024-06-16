package com.goev.partner.event.events.vehicle.update;

import com.goev.lib.event.core.Event;
import com.goev.partner.dao.vehicle.transfer.VehicleTransferDetailDao;
import com.goev.partner.event.targets.CentralTarget;
import jakarta.annotation.PostConstruct;

public class VehicleTransferDetailUpdateEvent extends Event<VehicleTransferDetailDao> {

    @Override
    @PostConstruct
    public void init() {
        registerEventTargets(CentralTarget.getTargetName());
    }

    @Override
    public String getName() {
        return "VehicleTransferDetailUpdateEvent";
    }

}

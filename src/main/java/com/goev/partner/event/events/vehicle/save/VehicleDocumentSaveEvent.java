package com.goev.partner.event.events.vehicle.save;

import com.goev.lib.event.core.Event;
import com.goev.partner.dao.vehicle.document.VehicleDocumentDao;
import com.goev.partner.event.targets.CentralTarget;
import jakarta.annotation.PostConstruct;

public class VehicleDocumentSaveEvent extends Event<VehicleDocumentDao> {

    @Override
    @PostConstruct
    public void init() {
        registerEventTargets(CentralTarget.getTargetName());
    }

    @Override
    public String getName() {
        return "VehicleDocumentSaveEvent";
    }

}

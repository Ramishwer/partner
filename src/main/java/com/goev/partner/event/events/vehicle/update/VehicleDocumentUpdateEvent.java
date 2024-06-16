package com.goev.partner.event.events.vehicle.update;

import com.goev.lib.event.core.Event;
import com.goev.partner.dao.partner.document.PartnerDocumentDao;
import com.goev.partner.event.targets.CentralTarget;
import jakarta.annotation.PostConstruct;

public class VehicleDocumentUpdateEvent extends Event<PartnerDocumentDao> {

    @Override
    @PostConstruct
    public void init() {
        registerEventTargets(CentralTarget.getTargetName());
    }

    @Override
    public String getName() {
        return "VehicleDocumentUpdateEvent";
    }

}

package com.goev.partner.event.events.vehicle.update;

import com.goev.partner.dao.partner.document.PartnerDocumentDao;
import com.goev.partner.dao.vehicle.document.VehicleDocumentDao;
import com.goev.partner.event.targets.CentralTarget;
import com.goev.lib.event.core.Event;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class VehicleDocumentUpdateEvent extends Event<VehicleDocumentDao> {

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

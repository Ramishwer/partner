package com.goev.partner.event.events.vehicle.save;

import com.goev.partner.dao.vehicle.document.VehicleDocumentDao;
import com.goev.partner.event.targets.PartnerTarget;
import com.goev.lib.event.core.Event;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class VehicleDocumentSaveEvent extends Event<VehicleDocumentDao> {

    @Override
    @PostConstruct
    public void init() {
        registerEventTargets(PartnerTarget.getTargetName());
    }

    @Override
    public String getName() {
        return "VehicleDocumentSaveEvent";
    }

}

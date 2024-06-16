package com.goev.partner.event.events.vehicle.update;

import com.goev.partner.dao.partner.document.PartnerDocumentTypeDao;
import com.goev.partner.event.targets.PartnerTarget;
import com.goev.lib.event.core.Event;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class VehicleDocumentTypeUpdateEvent extends Event<PartnerDocumentTypeDao> {

    @Override
    @PostConstruct
    public void init() {
        registerEventTargets(PartnerTarget.getTargetName());
    }

    @Override
    public String getName() {
        return "VehicleDocumentTypeAddEvent";
    }

}

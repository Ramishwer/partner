package com.goev.partner.event.events.vehicle.save;

import com.goev.partner.dao.vehicle.document.VehicleDocumentTypeDao;
import com.goev.partner.event.targets.CentralTarget;
import com.goev.lib.event.core.Event;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class VehicleDocumentTypeSaveEvent extends Event<VehicleDocumentTypeDao> {

    @Override
    @PostConstruct
    public void init() {
        registerEventTargets(CentralTarget.getTargetName());
    }

    @Override
    public String getName() {
        return "VehicleDocumentTypeSaveEvent";
    }

}

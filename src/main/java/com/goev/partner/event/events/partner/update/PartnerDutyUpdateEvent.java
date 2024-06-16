package com.goev.partner.event.events.partner.update;

import com.goev.lib.event.core.Event;
import com.goev.partner.dao.partner.duty.PartnerDutyDao;
import com.goev.partner.event.targets.CentralTarget;
import jakarta.annotation.PostConstruct;

public class PartnerDutyUpdateEvent extends Event<PartnerDutyDao> {

    @Override
    @PostConstruct
    public void init() {
        registerEventTargets(CentralTarget.getTargetName());
    }

    @Override
    public String getName() {
        return "PartnerDutyUpdateEvent";
    }

}

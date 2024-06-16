package com.goev.partner.event.events.partner.update;

import com.goev.partner.dao.partner.duty.PartnerDutyDao;
import com.goev.partner.event.targets.PartnerTarget;
import com.goev.lib.event.core.Event;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class PartnerDutyUpdateEvent extends Event<PartnerDutyDao> {

    @Override
    @PostConstruct
    public void init() {
        registerEventTargets(PartnerTarget.getTargetName());
    }

    @Override
    public String getName() {
        return "PartnerDutyUpdateEvent";
    }

}

package com.goev.partner.event.events.partner.update;

import com.goev.lib.event.core.Event;
import com.goev.partner.dao.partner.detail.PartnerDao;
import com.goev.partner.event.targets.CentralTarget;
import jakarta.annotation.PostConstruct;

public class PartnerUpdateEvent extends Event<PartnerDao> {

    @Override
    @PostConstruct
    public void init() {
        registerEventTargets(CentralTarget.getTargetName());
    }

    @Override
    public String getName() {
        return "PartnerUpdateEvent";
    }

}

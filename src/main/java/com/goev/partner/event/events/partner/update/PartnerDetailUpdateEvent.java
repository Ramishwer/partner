package com.goev.partner.event.events.partner.update;

import com.goev.lib.event.core.Event;
import com.goev.partner.dao.partner.detail.PartnerDetailDao;
import com.goev.partner.event.targets.CentralTarget;
import jakarta.annotation.PostConstruct;

public class PartnerDetailUpdateEvent extends Event<PartnerDetailDao> {

    @Override
    @PostConstruct
    public void init() {
        registerEventTargets(CentralTarget.getTargetName());
    }

    @Override
    public String getName() {
        return "PartnerDetailUpdateEvent";
    }

}

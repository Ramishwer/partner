package com.goev.partner.event.events.partner.update;

import com.goev.partner.dao.partner.payout.PartnerPayoutDao;
import com.goev.partner.event.targets.CentralTarget;
import com.goev.lib.event.core.Event;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class PartnerPayoutUpdateEvent extends Event<PartnerPayoutDao> {

    @Override
    @PostConstruct
    public void init() {
        registerEventTargets(CentralTarget.getTargetName());
    }

    @Override
    public String getName() {
        return "PartnerPayoutUpdateEvent";
    }

}

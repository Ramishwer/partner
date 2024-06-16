package com.goev.partner.event.events.partner.save;

import com.goev.lib.event.core.Event;
import com.goev.partner.dao.partner.duty.PartnerShiftDao;
import com.goev.partner.event.targets.CentralTarget;
import jakarta.annotation.PostConstruct;

public class PartnerShiftSaveEvent extends Event<PartnerShiftDao> {

    @Override
    @PostConstruct
    public void init() {
        registerEventTargets(CentralTarget.getTargetName());
    }

    @Override
    public String getName() {
        return "PartnerShiftSaveEvent";
    }

}

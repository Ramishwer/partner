package com.goev.partner.event.events.partner.save;

import com.goev.partner.dao.partner.detail.PartnerDao;
import com.goev.partner.event.targets.CentralTarget;
import com.goev.lib.event.core.Event;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class PartnerSaveEvent extends Event<PartnerDao> {

    @Override
    @PostConstruct
    public void init() {
        registerEventTargets(CentralTarget.getTargetName());
    }

    @Override
    public String getName() {
        return "PartnerSaveEvent";
    }

}

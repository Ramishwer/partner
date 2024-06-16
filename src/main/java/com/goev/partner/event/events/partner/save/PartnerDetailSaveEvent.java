package com.goev.partner.event.events.partner.save;

import com.goev.partner.dao.partner.detail.PartnerDetailDao;
import com.goev.partner.event.targets.PartnerTarget;
import com.goev.lib.event.core.Event;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class PartnerDetailSaveEvent extends Event<PartnerDetailDao> {

    @Override
    @PostConstruct
    public void init() {
        registerEventTargets(PartnerTarget.getTargetName());
    }

    @Override
    public String getName() {
        return "PartnerDetailSaveEvent";
    }

}

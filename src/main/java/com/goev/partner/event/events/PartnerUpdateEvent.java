package com.goev.partner.event.events;

import com.goev.lib.event.core.Event;
import com.goev.partner.dao.partner.detail.PartnerDao;
import com.goev.partner.event.targets.CentralTarget;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
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

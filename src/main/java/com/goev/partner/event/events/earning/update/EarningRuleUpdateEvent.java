package com.goev.partner.event.events.earning.update;

import com.goev.lib.event.core.Event;
import com.goev.partner.dao.earning.EarningRuleDao;
import com.goev.partner.event.targets.CentralTarget;
import jakarta.annotation.PostConstruct;

public class EarningRuleUpdateEvent extends Event<EarningRuleDao> {

    @Override
    @PostConstruct
    public void init() {
        registerEventTargets(CentralTarget.getTargetName());
    }

    @Override
    public String getName() {
        return "EarningRuleUpdateEvent";
    }
}

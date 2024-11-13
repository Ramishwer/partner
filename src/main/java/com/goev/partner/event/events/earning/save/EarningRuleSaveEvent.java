package com.goev.partner.event.events.earning.save;

import com.goev.lib.event.core.Event;
import com.goev.partner.dao.earning.EarningRuleDao;
import com.goev.partner.event.targets.CentralTarget;
import jakarta.annotation.PostConstruct;

public class EarningRuleSaveEvent extends Event<EarningRuleDao> {

    @Override
    @PostConstruct
    public void init() {
        registerEventTargets(CentralTarget.getTargetName());
    }

    @Override
    public String getName() {
        return "EarningRuleSaveEvent";
    }
}

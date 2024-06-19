package com.goev.partner.event.events.partner;


import com.goev.lib.event.core.Event;
import com.goev.partner.event.targets.CentralTarget;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class PartnerOnboardingStatusCheckEvent extends Event<String> {

    @Override
    @PostConstruct
    public void init() {
        registerEventTargets(CentralTarget.getTargetName());
    }

    @Override
    public String getName() {
        return "PartnerOnboardingStatusCheckEvent";
    }

}

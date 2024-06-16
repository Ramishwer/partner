package com.goev.partner.event.events.partner.update;

import com.goev.lib.event.core.Event;
import com.goev.partner.dao.partner.document.PartnerDocumentTypeDao;
import com.goev.partner.event.targets.CentralTarget;
import jakarta.annotation.PostConstruct;

public class PartnerDocumentTypeUpdateEvent extends Event<PartnerDocumentTypeDao> {

    @Override
    @PostConstruct
    public void init() {
        registerEventTargets(CentralTarget.getTargetName());
    }

    @Override
    public String getName() {
        return "PartnerDocumentTypeAddEvent";
    }

}

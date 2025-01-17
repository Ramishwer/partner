package com.goev.partner.event.events.asset.update;

import com.goev.partner.dao.asset.AssetTypeDao;
import com.goev.partner.event.targets.CentralTarget;
import com.goev.lib.event.core.Event;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class AssetTypeUpdateEvent extends Event<AssetTypeDao> {

    @Override
    @PostConstruct
    public void init() {
        registerEventTargets(CentralTarget.getTargetName());
    }

    @Override
    public String getName() {
        return "AssetTypeUpdateEvent";
    }

}

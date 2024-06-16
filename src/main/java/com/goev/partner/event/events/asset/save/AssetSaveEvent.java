package com.goev.partner.event.events.asset.save;

import com.goev.lib.event.core.Event;
import com.goev.partner.dao.asset.AssetDao;
import com.goev.partner.event.targets.CentralTarget;
import jakarta.annotation.PostConstruct;

public class AssetSaveEvent extends Event<AssetDao> {

    @Override
    @PostConstruct
    public void init() {
        registerEventTargets(CentralTarget.getTargetName());
    }

    @Override
    public String getName() {
        return "AssetSaveEvent";
    }

}

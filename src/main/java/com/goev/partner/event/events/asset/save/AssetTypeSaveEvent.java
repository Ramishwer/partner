package com.goev.partner.event.events.asset.save;

import com.goev.lib.event.core.Event;
import com.goev.partner.dao.asset.AssetTypeDao;
import com.goev.partner.event.targets.CentralTarget;
import jakarta.annotation.PostConstruct;

public class AssetTypeSaveEvent extends Event<AssetTypeDao> {

    @Override
    @PostConstruct
    public void init() {
        registerEventTargets(CentralTarget.getTargetName());
    }

    @Override
    public String getName() {
        return "AssetTypeSaveEvent";
    }

}

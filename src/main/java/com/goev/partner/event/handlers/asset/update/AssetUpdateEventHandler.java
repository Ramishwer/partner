package com.goev.partner.event.handlers.asset.update;

import com.goev.lib.event.core.Event;
import com.goev.lib.event.core.EventHandler;
import com.goev.partner.dao.asset.AssetDao;
import com.goev.partner.repository.asset.AssetRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class AssetUpdateEventHandler extends EventHandler<AssetDao> {

    private final AssetRepository assetRepository;

    @Override
    public boolean onEvent(Event<AssetDao> event) {
        log.info("Data:{}", event.getData());
        AssetDao assetDao = event.getData();
        if (assetDao == null) {
            log.info("Asset Data Null");
            return false;
        }
        AssetDao existing = assetRepository.findByUUID(assetDao.getUuid());
        if (existing != null) {
            assetDao.setId(existing.getId());
            assetDao.setUuid(existing.getUuid());
            assetRepository.update(assetDao);
            return true;
        }
        return false;
    }
}

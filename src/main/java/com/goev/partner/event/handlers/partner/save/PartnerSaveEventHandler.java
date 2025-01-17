package com.goev.partner.event.handlers.partner.save;

import com.goev.lib.event.core.Event;
import com.goev.lib.event.core.EventHandler;
import com.goev.partner.dao.partner.detail.PartnerDao;
import com.goev.partner.repository.partner.detail.PartnerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class PartnerSaveEventHandler extends EventHandler<PartnerDao> {

    private final PartnerRepository partnerRepository;

    @Override
    public boolean onEvent(Event<PartnerDao> event) {
        log.info("Data:{}", event.getData());
        PartnerDao partnerDao = event.getData();
        if (partnerDao == null) {
            log.info("Partner Data Null");
            return false;
        }
        PartnerDao existing = partnerRepository.findByUUID(partnerDao.getUuid());
        if (existing == null) {
            partnerRepository.save(partnerDao);
            return true;
        }
        return false;
    }
}

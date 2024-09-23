package com.goev.partner.event.handlers.partner.update;

import com.goev.lib.event.core.Event;
import com.goev.lib.event.core.EventHandler;
import com.goev.partner.dao.partner.detail.PartnerDao;
import com.goev.partner.dao.partner.duty.PartnerShiftDao;
import com.goev.partner.repository.partner.detail.PartnerRepository;
import com.goev.partner.repository.partner.duty.PartnerShiftRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class PartnerShiftUpdateEventHandler extends EventHandler<PartnerShiftDao> {

    private final PartnerShiftRepository partnerShiftRepository;

    @Override
    public boolean onEvent(Event<PartnerShiftDao> event) {
        log.info("Data:{}", event.getData());
        PartnerShiftDao partnerShiftDao = event.getData();
        if (partnerShiftDao == null) {
            log.info("Partner Data Null");
            return false;
        }
        PartnerShiftDao existing = partnerShiftRepository.findByUUID(partnerShiftDao.getUuid());
        if (existing != null) {
            partnerShiftDao.setId(existing.getId());
            partnerShiftDao.setUuid(existing.getUuid());
            partnerShiftRepository.update(partnerShiftDao);
            return true;
        }
        return false;
    }
}

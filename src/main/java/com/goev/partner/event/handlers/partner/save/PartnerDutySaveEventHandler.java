package com.goev.partner.event.handlers.partner.save;

import com.goev.lib.event.core.Event;
import com.goev.lib.event.core.EventHandler;
import com.goev.partner.dao.partner.duty.PartnerDutyDao;
import com.goev.partner.repository.partner.duty.PartnerDutyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class PartnerDutySaveEventHandler extends EventHandler<PartnerDutyDao> {

    private final PartnerDutyRepository partnerDutyRepository;

    @Override
    public boolean onEvent(Event<PartnerDutyDao> event) {
        log.info("Data:{}", event.getData());
        PartnerDutyDao partnerDutyDao = event.getData();
        if (partnerDutyDao == null) {
            log.info("Partner Data Null");
            return false;
        }
        PartnerDutyDao existing = partnerDutyRepository.findByUUID(partnerDutyDao.getUuid());
        if (existing == null) {
            partnerDutyRepository.save(partnerDutyDao);
            return true;
        }
        return false;
    }
}

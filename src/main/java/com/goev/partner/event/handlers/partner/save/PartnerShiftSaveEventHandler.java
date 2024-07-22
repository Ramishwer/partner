package com.goev.partner.event.handlers.partner.save;

import com.goev.lib.event.core.Event;
import com.goev.lib.event.core.EventHandler;
import com.goev.partner.dao.partner.duty.PartnerDutyDao;
import com.goev.partner.dao.partner.duty.PartnerShiftDao;
import com.goev.partner.repository.partner.duty.PartnerDutyRepository;
import com.goev.partner.repository.partner.duty.PartnerShiftRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class PartnerShiftSaveEventHandler extends EventHandler<PartnerShiftDao> {

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
        if (existing == null) {
            partnerShiftRepository.save(partnerShiftDao);
            return true;
        }
        return false;
    }
}

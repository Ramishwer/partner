package com.goev.partner.event.handlers.partner.update;

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
public class PartnerDutyUpdateEventHandler extends EventHandler<PartnerDutyDao> {

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
        if (existing != null) {
            partnerDutyDao.setId(existing.getId());
            partnerDutyDao.setUuid(existing.getUuid());
            partnerDutyDao.setCreatedBy(existing.getCreatedBy());
            partnerDutyDao.setUpdatedBy(existing.getUpdatedBy());
            partnerDutyDao.setCreatedOn(existing.getCreatedOn());
            partnerDutyDao.setUpdatedOn(existing.getUpdatedOn());
            partnerDutyDao.setIsActive(existing.getIsActive());
            partnerDutyDao.setState(existing.getState());
            partnerDutyDao.setApiSource(existing.getApiSource());
            partnerDutyDao.setNotes(existing.getNotes());
            partnerDutyRepository.update(partnerDutyDao);
            return true;
        }

        return false;
    }
}

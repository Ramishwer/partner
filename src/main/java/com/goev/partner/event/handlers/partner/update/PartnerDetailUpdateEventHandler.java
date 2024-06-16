package com.goev.partner.event.handlers.partner.update;

import com.goev.lib.event.core.Event;
import com.goev.lib.event.core.EventHandler;
import com.goev.partner.dao.partner.detail.PartnerDetailDao;
import com.goev.partner.repository.partner.detail.PartnerDetailRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class PartnerDetailUpdateEventHandler extends EventHandler<PartnerDetailDao> {

    private final PartnerDetailRepository partnerDetailRepository;

    @Override
    public boolean onEvent(Event<PartnerDetailDao> event) {
        log.info("Data:{}", event.getData());
        PartnerDetailDao partnerDetailDao = event.getData();
        if (partnerDetailDao == null) {
            log.info("Partner Data Null");
            return false;
        }
        PartnerDetailDao existing = partnerDetailRepository.findByUUID(partnerDetailDao.getUuid());
        if (existing != null) {
            partnerDetailDao.setId(existing.getId());
            partnerDetailDao.setUuid(existing.getUuid());
            partnerDetailDao.setCreatedBy(existing.getCreatedBy());
            partnerDetailDao.setUpdatedBy(existing.getUpdatedBy());
            partnerDetailDao.setCreatedOn(existing.getCreatedOn());
            partnerDetailDao.setUpdatedOn(existing.getUpdatedOn());
            partnerDetailDao.setIsActive(existing.getIsActive());
            partnerDetailDao.setState(existing.getState());
            partnerDetailDao.setApiSource(existing.getApiSource());
            partnerDetailDao.setNotes(existing.getNotes());
            partnerDetailRepository.update(partnerDetailDao);
            return true;
        }

        return false;
    }
}

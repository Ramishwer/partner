package com.goev.partner.event.handlers.partner.save;

import com.goev.lib.event.core.Event;
import com.goev.lib.event.core.EventHandler;
import com.goev.partner.dao.partner.payout.PartnerPayoutDao;
import com.goev.partner.repository.partner.payout.PartnerPayoutRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class PartnerPayoutSaveEventHandler extends EventHandler<PartnerPayoutDao> {

    private final PartnerPayoutRepository partnerPayoutRepository;

    @Override
    public boolean onEvent(Event<PartnerPayoutDao> event) {
        log.info("Data:{}", event.getData());
        PartnerPayoutDao partnerPayoutDao = event.getData();
        if (partnerPayoutDao == null) {
            log.info("Partner Data Null");
            return false;
        }
        PartnerPayoutDao existing = partnerPayoutRepository.findByUUID(partnerPayoutDao.getUuid());
        if (existing == null) {
            partnerPayoutRepository.save(partnerPayoutDao);
            return true;
        }
        return false;
    }
}

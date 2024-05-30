package com.goev.partner.event.handlers;

import com.goev.lib.event.core.Event;
import com.goev.lib.event.core.EventHandler;
import com.goev.partner.dao.partner.detail.PartnerDao;
import com.goev.partner.event.targets.CentralTarget;
import com.goev.partner.repository.partner.detail.PartnerRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class PartnerUpdateEventHandler extends EventHandler<PartnerDao> {

    private final PartnerRepository partnerRepository;

    @Override
    public boolean onEvent(Event<PartnerDao> event) {
        log.info("Data:{}",event.getData());
        PartnerDao  partnerDao= event.getData();
        if(partnerDao == null) {
            log.info("Partner Data Null");
            return false;
        }
        PartnerDao existing = partnerRepository.findByUUID(partnerDao.getUuid());
        if(existing!=null){
            partnerDao.setId(existing.getId());
            partnerDao.setUuid(existing.getUuid());
            partnerRepository.update(partnerDao);
        }else{
            partnerRepository.save(partnerDao);
        }

        return false;
    }
}

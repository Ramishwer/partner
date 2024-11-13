package com.goev.partner.event.handlers.earning.save;

import com.goev.lib.event.core.Event;
import com.goev.lib.event.core.EventHandler;
import com.goev.partner.dao.earning.EarningRuleDao;
import com.goev.partner.dao.partner.detail.PartnerDetailDao;
import com.goev.partner.repository.earning.EarningRuleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class EarningRuleSaveEventHandler extends EventHandler<EarningRuleDao> {
    final EarningRuleRepository earningRuleRepository;
    @Override
    public boolean onEvent(Event<EarningRuleDao> event) {
        log.info("Data:{}", event.getData());
        EarningRuleDao earningRuleDao = event.getData();
        if (earningRuleDao == null) {
            log.info("Earning Rule  Data Null");
            return false;
        }
        EarningRuleDao existing = earningRuleRepository.findByUuid(earningRuleDao.getUuid());
        if(existing == null){
            earningRuleRepository.save(earningRuleDao);
            return true;
        }
        return false;
    }
}
package com.goev.partner.repository.earning;

import com.goev.partner.dao.earning.EarningRuleDao;

import java.util.List;

public interface EarningRuleRepository {
    EarningRuleDao save(EarningRuleDao earningRuleDao);

    EarningRuleDao findByUuid(String uuid);

    EarningRuleDao update(EarningRuleDao earningRuleDao);

    List<EarningRuleDao> findAllByClientName(String clientName);
}

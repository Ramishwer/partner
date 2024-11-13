package com.goev.partner.repository.earning;

import com.goev.partner.dao.earning.EarningRuleDao;

public interface EarningRuleRepository {
    EarningRuleDao save(EarningRuleDao earningRuleDao);

    EarningRuleDao findByUuid(String uuid);

    EarningRuleDao update(EarningRuleDao earningRuleDao);
}

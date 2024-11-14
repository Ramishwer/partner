package com.goev.partner.service.earning;

import com.goev.partner.dao.asset.AssetDao;
import com.goev.partner.dao.earning.EarningRuleDao;

import java.util.List;

public interface EarningRuleService {

     List<EarningRuleDao> getEarningRulesByClientName(String clientName);
}

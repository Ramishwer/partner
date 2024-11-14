package com.goev.partner.service.earning.impl;

import com.goev.lib.exceptions.ResponseException;
import com.goev.partner.dao.earning.EarningRuleDao;
import com.goev.partner.repository.earning.EarningRuleRepository;
import com.goev.partner.service.earning.EarningRuleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class EarningRuleServiceImpl implements EarningRuleService {

    private final EarningRuleRepository earningRuleRepository;

    @Override
    public List<EarningRuleDao> getEarningRulesByClientName(String clientName) {
        try {
            List<EarningRuleDao> earningRules = earningRuleRepository.findAllByClientName(clientName);
            if (earningRules.isEmpty()) {
                throw new ResponseException(404, "No earning rules found for client: " + clientName);
            }
            return earningRules;
        } catch (Exception e) {
            log.error("Error retrieving earning rules for client: {}", clientName, e);
            throw new ResponseException(500, "Internal server error occurred while fetching earning rules",
                    Map.of("clientName", clientName));
        }
    }
}

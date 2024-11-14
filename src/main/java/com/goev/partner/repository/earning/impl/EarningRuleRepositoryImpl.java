package com.goev.partner.repository.earning.impl;
import com.goev.partner.dao.earning.EarningRuleDao;
import com.goev.partner.repository.earning.EarningRuleRepository;
import com.goev.partner.utilities.EventExecutorUtils;
import com.goev.partner.utilities.RequestContext;
import com.goev.record.partner.tables.records.EarningRuleRecord;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.goev.record.partner.tables.EarningRule.EARNING_RULE;
@Slf4j
@Repository
@AllArgsConstructor
public class EarningRuleRepositoryImpl implements EarningRuleRepository {
    private final DSLContext context;
    private final EventExecutorUtils eventExecutor;


    @Override
    public EarningRuleDao save(EarningRuleDao earningRuleDao){
        EarningRuleRecord earningRuleRecord = context.newRecord(EARNING_RULE, earningRuleDao);
        earningRuleRecord.setRuleId(earningRuleDao.getRuleId());
        earningRuleRecord.store();
        earningRuleDao.setUuid(earningRuleRecord.getUuid());
        earningRuleDao.setRuleId(earningRuleRecord.getRuleId());
        earningRuleDao.setCity(earningRuleRecord.getCity());
        earningRuleDao.setBusinessType(earningRuleRecord.getBusinessType());
        earningRuleDao.setClientName(earningRuleRecord.getClientName());
        earningRuleDao.setFixedIncome(earningRuleRecord.getFixedIncome());
        earningRuleDao.setVariableIncome(earningRuleRecord.getVariableIncome());
        earningRuleDao.setChecks(earningRuleRecord.getChecks());
        earningRuleDao.setCheckValue(earningRuleRecord.getCheckValue());
        earningRuleDao.setValidTill(earningRuleRecord.getValidTill());
        earningRuleDao.setIsActive(earningRuleRecord.getIsActive());
        earningRuleDao.setCreatedOn(earningRuleRecord.getCreatedOn());
        earningRuleDao.setCreatedBy(earningRuleRecord.getCreatedBy());
        if (!"EVENT".equals(RequestContext.getRequestSource()))
            eventExecutor.fireEvent("EarningRuleSaveEvent", earningRuleDao);
        return earningRuleDao;
    }

    public EarningRuleDao findByUuid(String uuid){
        return context.selectFrom(EARNING_RULE)
                .where(EARNING_RULE.UUID.eq(uuid))
                .and(EARNING_RULE.IS_ACTIVE.eq(true))
                .fetchAnyInto(EarningRuleDao.class);
    }

    @Override
    public EarningRuleDao update(EarningRuleDao earningRuleDao){
        EarningRuleRecord earningRuleRecord = context.newRecord(EARNING_RULE, earningRuleDao);
        earningRuleRecord.update();
        earningRuleDao.setUuid(earningRuleRecord.getUuid());
        earningRuleDao.setRuleId(earningRuleRecord.getRuleId());
        earningRuleDao.setCity(earningRuleRecord.getCity());
        earningRuleDao.setBusinessType(earningRuleRecord.getBusinessType());
        earningRuleDao.setClientName(earningRuleRecord.getClientName());
        earningRuleDao.setFixedIncome(earningRuleRecord.getFixedIncome());
        earningRuleDao.setVariableIncome(earningRuleRecord.getVariableIncome());
        earningRuleDao.setChecks(earningRuleRecord.getChecks());
        earningRuleDao.setCheckValue(earningRuleRecord.getCheckValue());
        earningRuleDao.setValidTill(earningRuleRecord.getValidTill());
        earningRuleDao.setIsActive(earningRuleRecord.getIsActive());
        earningRuleDao.setCreatedOn(earningRuleRecord.getCreatedOn());
        earningRuleDao.setCreatedBy(earningRuleRecord.getCreatedBy());
        if (!"EVENT".equals(RequestContext.getRequestSource()))
            eventExecutor.fireEvent("EarningRuleUpdateEvent", earningRuleDao);

        return earningRuleDao;
    }

              //findAll
    @Override
    public List<EarningRuleDao> findAllByClientName(String clientName) {
        return context.selectFrom(EARNING_RULES)
                    .where(EARNING_RULES.CLIENT_NAME.eq(clientName))
                    .fetchInto(EarningRuleDao.class);
        }
    }


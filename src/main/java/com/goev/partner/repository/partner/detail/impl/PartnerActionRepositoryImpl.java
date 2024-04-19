package com.goev.partner.repository.partner.detail.impl;

import com.goev.partner.dao.partner.detail.PartnerActionDao;
import com.goev.partner.repository.partner.detail.PartnerActionRepository;
import com.goev.lib.enums.RecordState;
import com.goev.record.partner.tables.records.PartnerActionsRecord;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.goev.record.partner.tables.PartnerActions.PARTNER_ACTIONS;

@Slf4j
@Repository
@AllArgsConstructor
public class PartnerActionRepositoryImpl implements PartnerActionRepository {

    private final DSLContext context;

    @Override
    public PartnerActionDao save(PartnerActionDao action) {
        PartnerActionsRecord partnerActionsRecord = context.newRecord(PARTNER_ACTIONS, action);
        partnerActionsRecord.store();
        action.setId(partnerActionsRecord.getId());
        action.setUuid(partnerActionsRecord.getUuid());
        return action;
    }

    @Override
    public PartnerActionDao update(PartnerActionDao action) {
        PartnerActionsRecord partnerActionsRecord = context.newRecord(PARTNER_ACTIONS, action);
        partnerActionsRecord.update();
        return action;
    }

    @Override
    public void delete(Integer id) {
        context.update(PARTNER_ACTIONS).set(PARTNER_ACTIONS.STATE, RecordState.DELETED.name()).where(PARTNER_ACTIONS.ID.eq(id)).execute();
    }

    @Override
    public PartnerActionDao findByUUID(String uuid) {
        return context.selectFrom(PARTNER_ACTIONS).where(PARTNER_ACTIONS.UUID.eq(uuid)).fetchAnyInto(PartnerActionDao.class);
    }

    @Override
    public PartnerActionDao findById(Integer id) {
        return context.selectFrom(PARTNER_ACTIONS).where(PARTNER_ACTIONS.ID.eq(id)).fetchAnyInto(PartnerActionDao.class);
    }

    @Override
    public List<PartnerActionDao> findAllByIds(List<Integer> ids) {
        return context.selectFrom(PARTNER_ACTIONS).where(PARTNER_ACTIONS.ID.in(ids)).fetchInto(PartnerActionDao.class);
    }

    @Override
    public List<PartnerActionDao> findAll() {
        return context.selectFrom(PARTNER_ACTIONS).fetchInto(PartnerActionDao.class);
    }
}
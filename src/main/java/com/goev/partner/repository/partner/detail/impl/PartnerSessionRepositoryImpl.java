package com.goev.partner.repository.partner.detail.impl;

import com.goev.partner.dao.partner.detail.PartnerSessionDao;
import com.goev.partner.repository.partner.detail.PartnerSessionRepository;
import com.goev.lib.enums.RecordState;
import com.goev.record.partner.tables.records.PartnerSessionsRecord;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.goev.record.partner.tables.PartnerSessions.PARTNER_SESSIONS;

@Slf4j
@Repository
@AllArgsConstructor
public class PartnerSessionRepositoryImpl implements PartnerSessionRepository {

    private final DSLContext context;

    @Override
    public PartnerSessionDao save(PartnerSessionDao session) {
        PartnerSessionsRecord partnerSessionsRecord = context.newRecord(PARTNER_SESSIONS, session);
        partnerSessionsRecord.store();
        session.setId(partnerSessionsRecord.getId());
        session.setUuid(partnerSessionsRecord.getUuid());
        return session;
    }

    @Override
    public PartnerSessionDao update(PartnerSessionDao session) {
        PartnerSessionsRecord partnerSessionsRecord = context.newRecord(PARTNER_SESSIONS, session);
        partnerSessionsRecord.update();
        return session;
    }

    @Override
    public void delete(Integer id) {
        context.update(PARTNER_SESSIONS).set(PARTNER_SESSIONS.STATE, RecordState.DELETED.name()).where(PARTNER_SESSIONS.ID.eq(id)).execute();
    }

    @Override
    public PartnerSessionDao findByUUID(String uuid) {
        return context.selectFrom(PARTNER_SESSIONS).where(PARTNER_SESSIONS.UUID.eq(uuid)).fetchAnyInto(PartnerSessionDao.class);
    }

    @Override
    public PartnerSessionDao findById(Integer id) {
        return context.selectFrom(PARTNER_SESSIONS).where(PARTNER_SESSIONS.ID.eq(id)).fetchAnyInto(PartnerSessionDao.class);
    }

    @Override
    public List<PartnerSessionDao> findAllByIds(List<Integer> ids) {
        return context.selectFrom(PARTNER_SESSIONS).where(PARTNER_SESSIONS.ID.in(ids)).fetchInto(PartnerSessionDao.class);
    }

    @Override
    public List<PartnerSessionDao> findAll() {
        return context.selectFrom(PARTNER_SESSIONS).fetchInto(PartnerSessionDao.class);
    }
}

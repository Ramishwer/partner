package com.goev.partner.repository.system.instance.impl;

import com.goev.partner.dao.system.instance.SystemInstanceDao;
import com.goev.partner.repository.system.instance.SystemInstanceRepository;
import com.goev.lib.enums.RecordState;
import com.goev.record.partner.tables.records.SystemInstancesRecord;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.goev.record.partner.tables.SystemInstances.SYSTEM_INSTANCES;

@Slf4j
@Repository
@AllArgsConstructor
public class SystemInstanceRepositoryImpl implements SystemInstanceRepository {
    private final DSLContext context;

    @Override
    public SystemInstanceDao save(SystemInstanceDao instance) {
        SystemInstancesRecord systemInstancesRecord = context.newRecord(SYSTEM_INSTANCES, instance);
        systemInstancesRecord.store();
        instance.setId(systemInstancesRecord.getId());
        instance.setUuid(systemInstancesRecord.getUuid());
        return instance;
    }

    @Override
    public SystemInstanceDao update(SystemInstanceDao instance) {
        SystemInstancesRecord systemInstancesRecord = context.newRecord(SYSTEM_INSTANCES, instance);
        systemInstancesRecord.update();
        return instance;
    }

    @Override
    public void delete(Integer id) {
        context.update(SYSTEM_INSTANCES).set(SYSTEM_INSTANCES.STATE, RecordState.DELETED.name()).where(SYSTEM_INSTANCES.ID.eq(id)).execute();
    }

    @Override
    public SystemInstanceDao findByUUID(String uuid) {
        return context.selectFrom(SYSTEM_INSTANCES).where(SYSTEM_INSTANCES.UUID.eq(uuid)).fetchAnyInto(SystemInstanceDao.class);
    }

    @Override
    public SystemInstanceDao findById(Integer id) {
        return context.selectFrom(SYSTEM_INSTANCES).where(SYSTEM_INSTANCES.ID.eq(id)).fetchAnyInto(SystemInstanceDao.class);
    }

    @Override
    public List<SystemInstanceDao> findAllByIds(List<Integer> ids) {
        return context.selectFrom(SYSTEM_INSTANCES).where(SYSTEM_INSTANCES.ID.in(ids)).fetchInto(SystemInstanceDao.class);
    }

    @Override
    public List<SystemInstanceDao> findAll() {
        return context.selectFrom(SYSTEM_INSTANCES).fetchInto(SystemInstanceDao.class);
    }
}
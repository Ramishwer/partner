package com.goev.partner.repository.system.property.impl;

import com.goev.partner.dao.system.property.SystemPropertyDao;
import com.goev.partner.repository.system.property.SystemPropertyRepository;
import com.goev.lib.enums.RecordState;
import com.goev.record.partner.tables.records.SystemPropertiesRecord;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static com.goev.record.partner.tables.SystemProperties.SYSTEM_PROPERTIES;

@Slf4j
@Repository
@AllArgsConstructor
public class SystemPropertyRepositoryImpl implements SystemPropertyRepository {
    private final DSLContext context;

    @Override
    public Map<String, SystemPropertyDao> getPropertyMap() {
        return context.select().from(SYSTEM_PROPERTIES)
                .where(SYSTEM_PROPERTIES.IS_ACTIVE.eq(true))
                .fetchMap(SYSTEM_PROPERTIES.PROPERTY_NAME, SystemPropertyDao.class);
    }

    @Override
    public SystemPropertyDao save(SystemPropertyDao property) {
        SystemPropertiesRecord systemPropertiesRecord = context.newRecord(SYSTEM_PROPERTIES, property);
        systemPropertiesRecord.store();
        property.setId(systemPropertiesRecord.getId());
        property.setUuid(systemPropertiesRecord.getUuid());
        return property;
    }

    @Override
    public SystemPropertyDao update(SystemPropertyDao property) {
        SystemPropertiesRecord systemPropertiesRecord = context.newRecord(SYSTEM_PROPERTIES, property);
        systemPropertiesRecord.update();
        return property;
    }

    @Override
    public void delete(Integer id) {
        context.update(SYSTEM_PROPERTIES).set(SYSTEM_PROPERTIES.STATE, RecordState.DELETED.name()).where(SYSTEM_PROPERTIES.ID.eq(id)).execute();
    }

    @Override
    public SystemPropertyDao findByUUID(String uuid) {
        return context.selectFrom(SYSTEM_PROPERTIES).where(SYSTEM_PROPERTIES.UUID.eq(uuid)).fetchAnyInto(SystemPropertyDao.class);
    }

    @Override
    public SystemPropertyDao findById(Integer id) {
        return context.selectFrom(SYSTEM_PROPERTIES).where(SYSTEM_PROPERTIES.ID.eq(id)).fetchAnyInto(SystemPropertyDao.class);
    }

    @Override
    public List<SystemPropertyDao> findAllByIds(List<Integer> ids) {
        return context.selectFrom(SYSTEM_PROPERTIES).where(SYSTEM_PROPERTIES.ID.in(ids)).fetchInto(SystemPropertyDao.class);
    }

    @Override
    public List<SystemPropertyDao> findAll() {
        return context.selectFrom(SYSTEM_PROPERTIES).fetchInto(SystemPropertyDao.class);
    }
}
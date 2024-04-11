package com.goev.partner.repository.location.impl;

import com.goev.partner.dao.location.LocationDetailDao;
import com.goev.partner.repository.location.LocationDetailRepository;
import com.goev.lib.enums.RecordState;
import com.goev.record.partner.tables.records.LocationDetailsRecord;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.goev.record.partner.tables.LocationDetails.LOCATION_DETAILS;

@Slf4j
@Repository
@AllArgsConstructor
public class LocationDetailRepositoryImpl implements LocationDetailRepository {

    private final DSLContext context;

    @Override
    public LocationDetailDao save(LocationDetailDao locationDetail) {
        LocationDetailsRecord locationDetailsRecord = context.newRecord(LOCATION_DETAILS, locationDetail);
        locationDetailsRecord.store();
        locationDetail.setId(locationDetailsRecord.getId());
        locationDetail.setUuid(locationDetailsRecord.getUuid());
        return locationDetail;
    }

    @Override
    public LocationDetailDao update(LocationDetailDao locationDetail) {
        LocationDetailsRecord locationDetailsRecord = context.newRecord(LOCATION_DETAILS, locationDetail);
        locationDetailsRecord.update();
        return locationDetail;
    }

    @Override
    public void delete(Integer id) {
        context.update(LOCATION_DETAILS).set(LOCATION_DETAILS.STATE, RecordState.DELETED.name()).where(LOCATION_DETAILS.ID.eq(id)).execute();
    }

    @Override
    public LocationDetailDao findByUUID(String uuid) {
        return context.selectFrom(LOCATION_DETAILS).where(LOCATION_DETAILS.UUID.eq(uuid)).fetchAnyInto(LocationDetailDao.class);
    }

    @Override
    public LocationDetailDao findById(Integer id) {
        return context.selectFrom(LOCATION_DETAILS).where(LOCATION_DETAILS.ID.eq(id)).fetchAnyInto(LocationDetailDao.class);
    }

    @Override
    public List<LocationDetailDao> findAllByIds(List<Integer> ids) {
        return context.selectFrom(LOCATION_DETAILS).where(LOCATION_DETAILS.ID.in(ids)).fetchInto(LocationDetailDao.class);
    }

    @Override
    public List<LocationDetailDao> findAll() {
        return context.selectFrom(LOCATION_DETAILS).fetchInto(LocationDetailDao.class);
    }
}

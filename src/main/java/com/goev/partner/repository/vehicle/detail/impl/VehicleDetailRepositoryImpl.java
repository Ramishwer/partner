package com.goev.partner.repository.vehicle.detail.impl;

import com.goev.partner.dao.vehicle.detail.VehicleDetailDao;
import com.goev.partner.repository.vehicle.detail.VehicleDetailRepository;
import com.goev.lib.enums.RecordState;
import com.goev.record.partner.tables.records.VehicleDetailsRecord;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.goev.record.partner.tables.VehicleDetails.VEHICLE_DETAILS;

@Repository
@AllArgsConstructor
@Slf4j
public class VehicleDetailRepositoryImpl implements VehicleDetailRepository {
    private final DSLContext context;

    @Override
    public VehicleDetailDao save(VehicleDetailDao vehicleDetails) {
        VehicleDetailsRecord vehicleDetailsRecord = context.newRecord(VEHICLE_DETAILS, vehicleDetails);
        vehicleDetailsRecord.store();
        vehicleDetails.setId(vehicleDetailsRecord.getId());
        vehicleDetails.setUuid(vehicleDetails.getUuid());
        return vehicleDetails;
    }

    @Override
    public VehicleDetailDao update(VehicleDetailDao vehicleDetails) {
        VehicleDetailsRecord vehicleDetailsRecord = context.newRecord(VEHICLE_DETAILS, vehicleDetails);
        vehicleDetailsRecord.update();
        return vehicleDetails;
    }

    @Override
    public void delete(Integer id) {
        context.update(VEHICLE_DETAILS).set(VEHICLE_DETAILS.STATE, RecordState.DELETED.name()).where(VEHICLE_DETAILS.ID.eq(id)).execute();
    }

    @Override
    public VehicleDetailDao findByUUID(String uuid) {
        return context.selectFrom(VEHICLE_DETAILS).where(VEHICLE_DETAILS.UUID.eq(uuid)).fetchAnyInto(VehicleDetailDao.class);
    }

    @Override
    public VehicleDetailDao findById(Integer id) {
        return context.selectFrom(VEHICLE_DETAILS).where(VEHICLE_DETAILS.ID.eq(id)).fetchAnyInto(VehicleDetailDao.class);
    }

    @Override
    public List<VehicleDetailDao> findAllByIds(List<Integer> ids) {
        return context.selectFrom(VEHICLE_DETAILS).where(VEHICLE_DETAILS.ID.in(ids)).fetchInto(VehicleDetailDao.class);
    }

    @Override
    public List<VehicleDetailDao> findAll() {
        return context.selectFrom(VEHICLE_DETAILS).fetchInto(VehicleDetailDao.class);
    }
}

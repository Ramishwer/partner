package com.goev.partner.repository.vehicle.detail.impl;

import com.goev.lib.enums.RecordState;
import com.goev.partner.dao.vehicle.detail.VehicleDao;
import com.goev.partner.repository.vehicle.detail.VehicleRepository;
import com.goev.record.partner.tables.records.VehiclesRecord;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.goev.record.partner.tables.Vehicles.VEHICLES;

@Repository
@AllArgsConstructor
@Slf4j
public class VehicleRepositoryImpl implements VehicleRepository {
    private final DSLContext context;

    @Override
    public VehicleDao save(VehicleDao vehicle) {
        VehiclesRecord vehiclesRecord = context.newRecord(VEHICLES, vehicle);
        vehiclesRecord.store();
        vehicle.setId(vehiclesRecord.getId());
        vehicle.setUuid(vehiclesRecord.getUuid());
        vehicle.setCreatedBy(vehiclesRecord.getCreatedBy());
        vehicle.setUpdatedBy(vehiclesRecord.getUpdatedBy());
        vehicle.setCreatedOn(vehiclesRecord.getCreatedOn());
        vehicle.setUpdatedOn(vehiclesRecord.getUpdatedOn());
        vehicle.setIsActive(vehiclesRecord.getIsActive());
        vehicle.setState(vehiclesRecord.getState());
        vehicle.setApiSource(vehiclesRecord.getApiSource());
        vehicle.setNotes(vehiclesRecord.getNotes());
        return vehicle;
    }

    @Override
    public VehicleDao update(VehicleDao vehicle) {
        VehiclesRecord vehiclesRecord = context.newRecord(VEHICLES, vehicle);
        vehiclesRecord.update();


        vehicle.setCreatedBy(vehiclesRecord.getCreatedBy());
        vehicle.setUpdatedBy(vehiclesRecord.getUpdatedBy());
        vehicle.setCreatedOn(vehiclesRecord.getCreatedOn());
        vehicle.setUpdatedOn(vehiclesRecord.getUpdatedOn());
        vehicle.setIsActive(vehiclesRecord.getIsActive());
        vehicle.setState(vehiclesRecord.getState());
        vehicle.setApiSource(vehiclesRecord.getApiSource());
        vehicle.setNotes(vehiclesRecord.getNotes());
        return vehicle;
    }

    @Override
    public void delete(Integer id) {
        context.update(VEHICLES)
                .set(VEHICLES.STATE, RecordState.DELETED.name())
                .where(VEHICLES.ID.eq(id))
                .and(VEHICLES.STATE.eq(RecordState.ACTIVE.name()))
                .and(VEHICLES.IS_ACTIVE.eq(true))
                .execute();
    }

    @Override
    public VehicleDao findByUUID(String uuid) {
        return context.selectFrom(VEHICLES).where(VEHICLES.UUID.eq(uuid))
                .and(VEHICLES.IS_ACTIVE.eq(true))
                .fetchAnyInto(VehicleDao.class);
    }

    @Override
    public VehicleDao findById(Integer id) {
        return context.selectFrom(VEHICLES).where(VEHICLES.ID.eq(id))
                .and(VEHICLES.IS_ACTIVE.eq(true))
                .fetchAnyInto(VehicleDao.class);
    }

    @Override
    public List<VehicleDao> findAllByIds(List<Integer> ids) {
        return context.selectFrom(VEHICLES).where(VEHICLES.ID.in(ids)).fetchInto(VehicleDao.class);
    }

    @Override
    public List<VehicleDao> findAllActive() {
        return context.selectFrom(VEHICLES).fetchInto(VehicleDao.class);
    }

    @Override
    public VehicleDao findByPlateNumber(String plateNumber) {
        return context.selectFrom(VEHICLES).where(VEHICLES.PLATE_NUMBER.eq(plateNumber)).fetchAnyInto(VehicleDao.class);
    }
}

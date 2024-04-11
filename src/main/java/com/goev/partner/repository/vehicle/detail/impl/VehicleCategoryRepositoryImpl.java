package com.goev.partner.repository.vehicle.detail.impl;

import com.goev.partner.dao.vehicle.detail.VehicleCategoryDao;
import com.goev.partner.repository.vehicle.detail.VehicleCategoryRepository;
import com.goev.lib.enums.RecordState;
import com.goev.record.partner.tables.records.VehicleCategoriesRecord;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.goev.record.partner.tables.VehicleCategories.VEHICLE_CATEGORIES;

@Repository
@AllArgsConstructor
@Slf4j
public class VehicleCategoryRepositoryImpl implements VehicleCategoryRepository {
    private final DSLContext context;

    @Override
    public VehicleCategoryDao save(VehicleCategoryDao category) {
        VehicleCategoriesRecord vehicleCategoriesRecord = context.newRecord(VEHICLE_CATEGORIES, category);
        vehicleCategoriesRecord.store();
        category.setId(vehicleCategoriesRecord.getId());
        category.setUuid(vehicleCategoriesRecord.getUuid());
        return category;
    }

    @Override
    public VehicleCategoryDao update(VehicleCategoryDao category) {
        VehicleCategoriesRecord vehicleCategoriesRecord = context.newRecord(VEHICLE_CATEGORIES, category);
        vehicleCategoriesRecord.update();
        return category;
    }

    @Override
    public void delete(Integer id) {
        context.update(VEHICLE_CATEGORIES).set(VEHICLE_CATEGORIES.STATE, RecordState.DELETED.name()).where(VEHICLE_CATEGORIES.ID.eq(id)).execute();
    }

    @Override
    public VehicleCategoryDao findByUUID(String uuid) {
        return context.selectFrom(VEHICLE_CATEGORIES).where(VEHICLE_CATEGORIES.UUID.eq(uuid)).fetchAnyInto(VehicleCategoryDao.class);
    }

    @Override
    public VehicleCategoryDao findById(Integer id) {
        return context.selectFrom(VEHICLE_CATEGORIES).where(VEHICLE_CATEGORIES.ID.eq(id)).fetchAnyInto(VehicleCategoryDao.class);
    }

    @Override
    public List<VehicleCategoryDao> findAllByIds(List<Integer> ids) {
        return context.selectFrom(VEHICLE_CATEGORIES).where(VEHICLE_CATEGORIES.ID.in(ids)).fetchInto(VehicleCategoryDao.class);
    }

    @Override
    public List<VehicleCategoryDao> findAll() {
        return context.selectFrom(VEHICLE_CATEGORIES).fetchInto(VehicleCategoryDao.class);
    }
}

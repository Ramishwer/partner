package com.goev.partner.repository.vehicle.transfer.impl;

import com.goev.lib.enums.RecordState;
import com.goev.partner.dao.vehicle.transfer.VehicleTransferDetailDao;
import com.goev.partner.repository.vehicle.transfer.VehicleTransferDetailRepository;
import com.goev.partner.utilities.EventExecutorUtils;
import com.goev.partner.utilities.RequestContext;
import com.goev.record.partner.tables.records.VehicleTransferDetailsRecord;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.goev.record.partner.tables.VehicleTransferDetails.VEHICLE_TRANSFER_DETAILS;

@Repository
@AllArgsConstructor
@Slf4j
public class VehicleTransferDetailRepositoryImpl implements VehicleTransferDetailRepository {
    private final DSLContext context;
    private final EventExecutorUtils eventExecutor;


    @Override
    public VehicleTransferDetailDao save(VehicleTransferDetailDao transferDetail) {
        VehicleTransferDetailsRecord vehicleTransferDetailsRecord = context.newRecord(VEHICLE_TRANSFER_DETAILS, transferDetail);
        vehicleTransferDetailsRecord.store();
        transferDetail.setId(vehicleTransferDetailsRecord.getId());
        transferDetail.setUuid(vehicleTransferDetailsRecord.getUuid());
        transferDetail.setCreatedBy(vehicleTransferDetailsRecord.getCreatedBy());
        transferDetail.setUpdatedBy(vehicleTransferDetailsRecord.getUpdatedBy());
        transferDetail.setCreatedOn(vehicleTransferDetailsRecord.getCreatedOn());
        transferDetail.setUpdatedOn(vehicleTransferDetailsRecord.getUpdatedOn());
        transferDetail.setIsActive(vehicleTransferDetailsRecord.getIsActive());
        transferDetail.setState(vehicleTransferDetailsRecord.getState());
        transferDetail.setApiSource(vehicleTransferDetailsRecord.getApiSource());
        transferDetail.setNotes(vehicleTransferDetailsRecord.getNotes());
        if(!"EVENT".equals(RequestContext.getRequestSource()))
            eventExecutor.fireEvent("VehicleTransferDetailSaveEvent", transferDetail);
        return transferDetail;
    }

    @Override
    public VehicleTransferDetailDao update(VehicleTransferDetailDao transferDetail) {
        VehicleTransferDetailsRecord vehicleTransferDetailsRecord = context.newRecord(VEHICLE_TRANSFER_DETAILS, transferDetail);
        vehicleTransferDetailsRecord.update();


        transferDetail.setCreatedBy(vehicleTransferDetailsRecord.getCreatedBy());
        transferDetail.setUpdatedBy(vehicleTransferDetailsRecord.getUpdatedBy());
        transferDetail.setCreatedOn(vehicleTransferDetailsRecord.getCreatedOn());
        transferDetail.setUpdatedOn(vehicleTransferDetailsRecord.getUpdatedOn());
        transferDetail.setIsActive(vehicleTransferDetailsRecord.getIsActive());
        transferDetail.setState(vehicleTransferDetailsRecord.getState());
        transferDetail.setApiSource(vehicleTransferDetailsRecord.getApiSource());
        transferDetail.setNotes(vehicleTransferDetailsRecord.getNotes());

        if(!"EVENT".equals(RequestContext.getRequestSource()))
            eventExecutor.fireEvent("VehicleTransferDetailUpdateEvent", transferDetail);
        return transferDetail;
    }

    @Override
    public void delete(Integer id) {
        context.update(VEHICLE_TRANSFER_DETAILS)
                .set(VEHICLE_TRANSFER_DETAILS.STATE, RecordState.DELETED.name())
                .where(VEHICLE_TRANSFER_DETAILS.ID.eq(id))
                .and(VEHICLE_TRANSFER_DETAILS.STATE.eq(RecordState.ACTIVE.name()))
                .and(VEHICLE_TRANSFER_DETAILS.IS_ACTIVE.eq(true))
                .execute();
    }

    @Override
    public VehicleTransferDetailDao findByUUID(String uuid) {
        return context.selectFrom(VEHICLE_TRANSFER_DETAILS).where(VEHICLE_TRANSFER_DETAILS.UUID.eq(uuid))
                .and(VEHICLE_TRANSFER_DETAILS.IS_ACTIVE.eq(true))
                .fetchAnyInto(VehicleTransferDetailDao.class);
    }

    @Override
    public VehicleTransferDetailDao findById(Integer id) {
        return context.selectFrom(VEHICLE_TRANSFER_DETAILS).where(VEHICLE_TRANSFER_DETAILS.ID.eq(id))
                .and(VEHICLE_TRANSFER_DETAILS.IS_ACTIVE.eq(true))
                .fetchAnyInto(VehicleTransferDetailDao.class);
    }

    @Override
    public List<VehicleTransferDetailDao> findAllByIds(List<Integer> ids) {
        return context.selectFrom(VEHICLE_TRANSFER_DETAILS).where(VEHICLE_TRANSFER_DETAILS.ID.in(ids)).fetchInto(VehicleTransferDetailDao.class);
    }

    @Override
    public List<VehicleTransferDetailDao> findAllActive() {
        return context.selectFrom(VEHICLE_TRANSFER_DETAILS).fetchInto(VehicleTransferDetailDao.class);
    }
}

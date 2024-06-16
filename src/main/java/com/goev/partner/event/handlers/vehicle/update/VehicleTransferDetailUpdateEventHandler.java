package com.goev.partner.event.handlers.vehicle.update;

import com.goev.lib.event.core.Event;
import com.goev.lib.event.core.EventHandler;
import com.goev.partner.dao.vehicle.transfer.VehicleTransferDetailDao;
import com.goev.partner.repository.vehicle.transfer.VehicleTransferDetailRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class VehicleTransferDetailUpdateEventHandler extends EventHandler<VehicleTransferDetailDao> {

    private final VehicleTransferDetailRepository vehicleTransferDetailRepository;

    @Override
    public boolean onEvent(Event<VehicleTransferDetailDao> event) {
        log.info("Data:{}", event.getData());
        VehicleTransferDetailDao vehicleTransferDetailDao = event.getData();
        if (vehicleTransferDetailDao == null) {
            log.info("Vehicle Data Null");
            return false;
        }
        VehicleTransferDetailDao existing = vehicleTransferDetailRepository.findByUUID(vehicleTransferDetailDao.getUuid());
        if (existing != null) {
            vehicleTransferDetailDao.setId(existing.getId());
            vehicleTransferDetailDao.setUuid(existing.getUuid());
            vehicleTransferDetailDao.setCreatedBy(existing.getCreatedBy());
            vehicleTransferDetailDao.setUpdatedBy(existing.getUpdatedBy());
            vehicleTransferDetailDao.setCreatedOn(existing.getCreatedOn());
            vehicleTransferDetailDao.setUpdatedOn(existing.getUpdatedOn());
            vehicleTransferDetailDao.setIsActive(existing.getIsActive());
            vehicleTransferDetailDao.setState(existing.getState());
            vehicleTransferDetailDao.setApiSource(existing.getApiSource());
            vehicleTransferDetailDao.setNotes(existing.getNotes());
            vehicleTransferDetailRepository.update(vehicleTransferDetailDao);
            return true;
        }
        return false;
    }
}

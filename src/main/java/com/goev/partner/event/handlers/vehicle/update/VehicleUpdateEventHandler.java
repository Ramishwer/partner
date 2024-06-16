package com.goev.partner.event.handlers.vehicle.update;

import com.goev.lib.event.core.Event;
import com.goev.lib.event.core.EventHandler;
import com.goev.partner.dao.vehicle.detail.VehicleDao;
import com.goev.partner.repository.vehicle.detail.VehicleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class VehicleUpdateEventHandler extends EventHandler<VehicleDao> {

    private final VehicleRepository vehicleRepository;

    @Override
    public boolean onEvent(Event<VehicleDao> event) {
        log.info("Data:{}", event.getData());
        VehicleDao vehicleDao = event.getData();
        if (vehicleDao == null) {
            log.info("Vehicle Data Null");
            return false;
        }
        VehicleDao existing = vehicleRepository.findByUUID(vehicleDao.getUuid());
        if (existing != null) {
            vehicleDao.setId(existing.getId());
            vehicleDao.setUuid(existing.getUuid());
            vehicleDao.setCreatedBy(existing.getCreatedBy());
            vehicleDao.setUpdatedBy(existing.getUpdatedBy());
            vehicleDao.setCreatedOn(existing.getCreatedOn());
            vehicleDao.setUpdatedOn(existing.getUpdatedOn());
            vehicleDao.setIsActive(existing.getIsActive());
            vehicleDao.setState(existing.getState());
            vehicleDao.setApiSource(existing.getApiSource());
            vehicleDao.setNotes(existing.getNotes());
            vehicleRepository.update(vehicleDao);
            return true;
        }
        return false;
    }
}

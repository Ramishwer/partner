package com.goev.partner.event.handlers.vehicle.update;

import com.goev.lib.event.core.Event;
import com.goev.lib.event.core.EventHandler;
import com.goev.partner.dao.vehicle.document.VehicleDocumentTypeDao;
import com.goev.partner.repository.vehicle.document.VehicleDocumentTypeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class VehicleDocumentTypeUpdateEventHandler extends EventHandler<VehicleDocumentTypeDao> {

    private final VehicleDocumentTypeRepository vehicleDocumentTypeRepository;

    @Override
    public boolean onEvent(Event<VehicleDocumentTypeDao> event) {
        log.info("Data:{}", event.getData());
        VehicleDocumentTypeDao vehicleDocumentTypeDao = event.getData();
        if (vehicleDocumentTypeDao == null) {
            log.info("VehicleDocumentType Data Null");
            return false;
        }
        VehicleDocumentTypeDao existing = vehicleDocumentTypeRepository.findByUUID(vehicleDocumentTypeDao.getUuid());
        if (existing != null) {
            vehicleDocumentTypeDao.setId(existing.getId());
            vehicleDocumentTypeDao.setUuid(existing.getUuid());
            vehicleDocumentTypeDao.setCreatedBy(existing.getCreatedBy());
            vehicleDocumentTypeDao.setUpdatedBy(existing.getUpdatedBy());
            vehicleDocumentTypeDao.setCreatedOn(existing.getCreatedOn());
            vehicleDocumentTypeDao.setUpdatedOn(existing.getUpdatedOn());
            vehicleDocumentTypeDao.setIsActive(existing.getIsActive());
            vehicleDocumentTypeDao.setState(existing.getState());
            vehicleDocumentTypeDao.setApiSource(existing.getApiSource());
            vehicleDocumentTypeDao.setNotes(existing.getNotes());
            vehicleDocumentTypeRepository.update(vehicleDocumentTypeDao);
            return true;
        }
        return false;
    }
}

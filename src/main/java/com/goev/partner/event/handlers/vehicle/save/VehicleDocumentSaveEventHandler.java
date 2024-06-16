package com.goev.partner.event.handlers.vehicle.save;

import com.goev.lib.event.core.Event;
import com.goev.lib.event.core.EventHandler;
import com.goev.partner.dao.vehicle.document.VehicleDocumentDao;
import com.goev.partner.repository.vehicle.document.VehicleDocumentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class VehicleDocumentSaveEventHandler extends EventHandler<VehicleDocumentDao> {

    private final VehicleDocumentRepository vehicleDocumentRepository;

    @Override
    public boolean onEvent(Event<VehicleDocumentDao> event) {
        log.info("Data:{}", event.getData());
        VehicleDocumentDao vehicleDocumentDao = event.getData();
        if (vehicleDocumentDao == null) {
            log.info("VehicleDocument Data Null");
            return false;
        }
        VehicleDocumentDao existing = vehicleDocumentRepository.findByUUID(vehicleDocumentDao.getUuid());
        if (existing != null) {
            vehicleDocumentRepository.save(vehicleDocumentDao);
            return true;
        }
        return false;
    }
}

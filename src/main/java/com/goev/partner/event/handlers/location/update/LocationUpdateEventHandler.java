package com.goev.partner.event.handlers.location.update;

import com.goev.lib.event.core.Event;
import com.goev.lib.event.core.EventHandler;
import com.goev.partner.dao.location.LocationDao;
import com.goev.partner.repository.location.LocationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class LocationUpdateEventHandler extends EventHandler<LocationDao> {

    private final LocationRepository locationRepository;

    @Override
    public boolean onEvent(Event<LocationDao> event) {
        log.info("Data:{}", event.getData());
        LocationDao locationDao = event.getData();
        if (locationDao == null) {
            log.info("Location Data Null");
            return false;
        }
        LocationDao existing = locationRepository.findByUUID(locationDao.getUuid());
        if (existing != null) {
            locationDao.setId(existing.getId());
            locationDao.setUuid(existing.getUuid());
            locationDao.setCreatedBy(existing.getCreatedBy());
            locationDao.setUpdatedBy(existing.getUpdatedBy());
            locationDao.setCreatedOn(existing.getCreatedOn());
            locationDao.setUpdatedOn(existing.getUpdatedOn());
            locationDao.setIsActive(existing.getIsActive());
            locationDao.setState(existing.getState());
            locationDao.setApiSource(existing.getApiSource());
            locationDao.setNotes(existing.getNotes());
            locationRepository.update(locationDao);
            return true;
        }
        return false;
    }
}

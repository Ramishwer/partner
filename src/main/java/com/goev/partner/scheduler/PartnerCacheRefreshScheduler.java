package com.goev.partner.scheduler;

import com.goev.partner.constant.ApplicationConstants;
import com.goev.partner.dao.partner.detail.PartnerDao;
import com.goev.partner.dao.vehicle.detail.VehicleDao;
import com.goev.partner.dto.vehicle.VehicleViewDto;
import com.goev.partner.enums.partner.PartnerStatus;
import com.goev.partner.enums.partner.PartnerSubStatus;
import com.goev.partner.enums.vehicle.VehicleStatus;
import com.goev.partner.repository.partner.detail.PartnerRepository;
import com.goev.partner.repository.vehicle.detail.VehicleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class PartnerCacheRefreshScheduler {
    private final VehicleRepository vehicleRepository;
    private final PartnerRepository partnerRepository;
//    @Scheduled(fixedRate = 1*60 * 1000)
    public void reportCurrentTime() {
        log.info("The {} time is now {}",this.getClass().getName() ,DateTime.now());

    }
}

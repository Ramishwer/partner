package com.goev.partner.controller;

import com.goev.partner.dao.partner.detail.PartnerDao;
import com.goev.partner.enums.partner.PartnerStatus;
import com.goev.partner.repository.partner.detail.PartnerRepository;
import com.goev.partner.utilities.EventExecutorUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class TestController {

    private final EventExecutorUtils eventExecutor;
    private final PartnerRepository partnerRepository;

    @GetMapping("/api/v1/test/event-schedule")
    public Boolean testEventSchedule() {
        PartnerDao partnerDao = partnerRepository.findByPhoneNumber("9457694060");
        partnerDao.setStatus(PartnerStatus.OFF_DUTY.name());

        eventExecutor.fireEvent("PartnerUpdateEvent",partnerDao, DateTime.now().plusMinutes(1));
        return true;
    }
}

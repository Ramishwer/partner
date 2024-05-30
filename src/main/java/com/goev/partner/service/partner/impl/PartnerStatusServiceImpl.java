package com.goev.partner.service.partner.impl;

import com.goev.partner.dto.partner.status.ActionDto;
import com.goev.partner.dto.partner.status.PartnerStatusDto;
import com.goev.partner.service.partner.PartnerStatusService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class PartnerStatusServiceImpl implements PartnerStatusService {
    @Override
    public PartnerStatusDto getStatusForPartner(String partnerUUID) {
        return null;
    }

    @Override
    public PartnerStatusDto savePartnerStatus(String partnerUUID, ActionDto actionDto) {
        return null;
    }
}

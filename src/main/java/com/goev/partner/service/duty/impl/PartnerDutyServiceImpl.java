package com.goev.partner.service.duty.impl;

import com.goev.partner.dto.common.PaginatedResponseDto;
import com.goev.partner.dto.partner.duty.PartnerDutyDto;
import com.goev.partner.service.duty.PartnerDutyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class PartnerDutyServiceImpl implements PartnerDutyService {
    @Override
    public PaginatedResponseDto<PartnerDutyDto> getDutiesForPartner(String partnerUUID, Integer count, Integer start) {
        return null;
    }

    @Override
    public PartnerDutyDto getDutyDetails(String partnerUUID, String dutyUUID) {
        return null;
    }
}

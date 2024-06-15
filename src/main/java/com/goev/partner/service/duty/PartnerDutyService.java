package com.goev.partner.service.duty;

import com.goev.partner.dto.common.PageDto;
import com.goev.partner.dto.common.PaginatedResponseDto;
import com.goev.partner.dto.partner.duty.PartnerDutyDto;

public interface PartnerDutyService {
    PaginatedResponseDto<PartnerDutyDto> getDutiesForPartner(String partnerUUID, PageDto page);

    PartnerDutyDto getDutyDetails(String partnerUUID, String dutyUUID);
}

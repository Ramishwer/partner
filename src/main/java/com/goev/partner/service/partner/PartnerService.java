package com.goev.partner.service.partner;

import com.goev.partner.dto.partner.detail.PartnerDetailDto;
import com.goev.partner.dto.partner.detail.PartnerDto;

public interface PartnerService {
    PartnerDetailDto getPartnerDetails(String partnerUUID);

    PartnerDetailDto updatePartnerDetails(String partnerUUID, PartnerDto partnerDto);
}

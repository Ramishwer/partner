package com.goev.partner.service.partner;

import com.goev.partner.dto.partner.detail.PartnerDetailDto;
import com.goev.partner.dto.partner.detail.PartnerDto;
import com.goev.partner.dto.partner.status.ActionDto;

public interface PartnerService {
    PartnerDetailDto getPartnerDetails(String partnerUUID);

    PartnerDto getPartner(String partnerUUID);

    PartnerDto updatePartner(String partnerUUID, ActionDto actionDto);

    PartnerDetailDto updatePartnerDetails(String partnerUUID, PartnerDetailDto partnerDetailDto);
}

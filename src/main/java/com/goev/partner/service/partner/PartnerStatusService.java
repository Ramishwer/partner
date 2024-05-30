package com.goev.partner.service.partner;


import com.goev.partner.dto.partner.status.ActionDto;
import com.goev.partner.dto.partner.status.PartnerStatusDto;

public interface PartnerStatusService {

    PartnerStatusDto getStatusForPartner(String partnerUUID);

    PartnerStatusDto savePartnerStatus(String partnerUUID, ActionDto actionDto);
}

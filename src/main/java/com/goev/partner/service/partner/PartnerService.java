package com.goev.partner.service.partner;

import com.goev.partner.dto.partner.detail.PartnerDetailsDto;
import com.goev.partner.dto.partner.document.PartnerDocumentDto;

public interface PartnerService {
    PartnerDetailsDto getPartnerDetails(String partnerUUID);

    PartnerDocumentDto createDocument(String partnerUUID, PartnerDocumentDto partnerDocumentDto);
}

package com.goev.partner.service.partner;

import com.goev.partner.dto.partner.document.PartnerDocumentDto;

public interface PartnerDocumentFileService {

    PartnerDocumentDto createPartnerFile(String partnerUUID, PartnerDocumentDto partnerDocumentDto);
}

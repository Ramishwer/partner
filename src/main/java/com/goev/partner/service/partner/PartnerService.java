package com.goev.partner.service.partner;

import com.goev.partner.dto.common.PaginatedResponseDto;
import com.goev.partner.dto.partner.detail.PartnerDetailsDto;
import com.goev.partner.dto.partner.document.PartnerDocumentDto;

import java.util.List;

public interface PartnerService {
    PartnerDetailsDto getPartnerDetails(String partnerUUID);
    List<PartnerDocumentDto> createDocument(String partnerUUID, List<PartnerDocumentDto> partnerDocumentDto);
    PaginatedResponseDto<PartnerDocumentDto> getDocuments(String partnerUUID);
}

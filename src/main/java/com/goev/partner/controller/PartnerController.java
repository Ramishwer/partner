package com.goev.partner.controller;

import com.goev.lib.dto.ResponseDto;
import com.goev.lib.dto.StatusDto;
import com.goev.partner.dto.common.PaginatedResponseDto;
import com.goev.partner.dto.partner.detail.PartnerDetailsDto;
import com.goev.partner.dto.partner.document.PartnerDocumentDto;
import com.goev.partner.service.partner.PartnerDocumentService;
import com.goev.partner.service.partner.PartnerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/partner-management/")
@AllArgsConstructor
public class PartnerController {

    private final PartnerService partnerService;
    private final PartnerDocumentService partnerDocumentService;
    @GetMapping("/partners/{partner-uuid}")
    public ResponseDto<PartnerDetailsDto> getPartnerDetails(@PathVariable(value = "partner-uuid")String partnerUUID){
        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(),200, partnerService.getPartnerDetails(partnerUUID));
    }

    @PostMapping("/partners/{partner-uuid}/documents")
    public ResponseDto<List<PartnerDocumentDto>> createDocument(@PathVariable(value = "partner-uuid")String partnerUUID, @RequestBody List<PartnerDocumentDto> partnerDocumentDto){
        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(),200, partnerDocumentService.createDocument(partnerUUID,partnerDocumentDto));
    }

    @GetMapping("/partners/{partner-uuid}/documents")
    public ResponseDto<PaginatedResponseDto<PartnerDocumentDto>> getDocuments(@PathVariable(value = "partner-uuid")String partnerUUID){
        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(),200, partnerDocumentService.getDocuments(partnerUUID));
    }
}

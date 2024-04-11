package com.goev.partner.controller;

import com.goev.lib.dto.ResponseDto;
import com.goev.lib.dto.StatusDto;
import com.goev.partner.dto.partner.detail.PartnerDetailsDto;
import com.goev.partner.dto.partner.document.PartnerDocumentDto;
import com.goev.partner.service.partner.PartnerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/partner-management/")
@AllArgsConstructor
public class PartnerController {

    private final PartnerService partnerService;
    @GetMapping("/partners/{partner-uuid}")
    public ResponseDto<PartnerDetailsDto> getPartnerDetails(@PathVariable(value = "partner-uuid")String partnerUUID){
        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(),200, partnerService.getPartnerDetails(partnerUUID));
    }

    @PostMapping("/partners/{partner-uuid}/documents")
    public ResponseDto<PartnerDocumentDto> createDocument(@PathVariable(value = "partner-uuid")String partnerUUID, @RequestBody PartnerDocumentDto partnerDocumentDto){
        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(),200, partnerService.createDocument(partnerUUID,partnerDocumentDto));
    }
}

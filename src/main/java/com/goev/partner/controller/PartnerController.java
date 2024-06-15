package com.goev.partner.controller;

import com.goev.lib.dto.ResponseDto;
import com.goev.lib.dto.StatusDto;
import com.goev.partner.dto.common.PaginatedResponseDto;
import com.goev.partner.dto.partner.detail.PartnerDetailDto;
import com.goev.partner.dto.partner.detail.PartnerDto;
import com.goev.partner.dto.partner.document.PartnerDocumentDto;
import com.goev.partner.dto.partner.status.ActionDto;
import com.goev.partner.dto.partner.status.PartnerStatusDto;
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
    public ResponseDto<PartnerDto> getPartner(@PathVariable("partner-uuid")String partnerUUID){
        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(),200,partnerService.getPartner(partnerUUID) );
    }

    @PostMapping("/partners/{partner-uuid}")
    public ResponseDto<PartnerDto> updatePartner(@PathVariable("partner-uuid")String partnerUUID, @RequestBody ActionDto actionDto){
        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(),200, partnerService.updatePartner(partnerUUID,actionDto));
    }
    @GetMapping("/partners/{partner-uuid}/details")
    public ResponseDto<PartnerDetailDto> getPartnerDetails(@PathVariable(value = "partner-uuid")String partnerUUID){
        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(),200, partnerService.getPartnerDetails(partnerUUID));
    }

    @PutMapping("/partners/{partner-uuid}/details")
    public ResponseDto<PartnerDetailDto> updatePartnerDetails(@PathVariable(value = "partner-uuid")String partnerUUID,@RequestBody PartnerDetailDto partnerDetailDto ){
        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(),200, partnerService.updatePartnerDetails(partnerUUID,partnerDetailDto));
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

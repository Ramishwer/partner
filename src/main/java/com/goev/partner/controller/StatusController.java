package com.goev.partner.controller;

import com.goev.lib.dto.ResponseDto;
import com.goev.lib.dto.StatusDto;
import com.goev.partner.dto.partner.payout.PartnerPayoutSummaryDto;
import com.goev.partner.dto.partner.status.ActionDto;
import com.goev.partner.dto.partner.status.PartnerStatusDto;
import com.goev.partner.service.partner.PartnerStatusService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/partner-management")
@AllArgsConstructor
public class StatusController {

    private PartnerStatusService partnerStatusService;
    @GetMapping("/partners/{partner-uuid}/status")
    public ResponseDto<PartnerStatusDto> getPartnerStatus(@PathVariable("partner-uuid")String partnerUUID){
        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(),200,partnerStatusService.getStatusForPartner(partnerUUID) );
    }

    @PostMapping("/partners/{partner-uuid}/status")
    public ResponseDto<PartnerStatusDto> savePartnerStatus(@PathVariable("partner-uuid")String partnerUUID, @RequestBody ActionDto actionDto){
        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(),200, partnerStatusService.savePartnerStatus(partnerUUID,actionDto));
    }

}

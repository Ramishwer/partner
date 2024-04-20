package com.goev.partner.controller;

import com.goev.lib.dto.ResponseDto;
import com.goev.lib.dto.StatusDto;
import com.goev.partner.dto.partner.status.ActionDto;
import com.goev.partner.dto.partner.status.PartnerStatusDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/partner-management")
public class StatusController {

    @GetMapping("/{partner-uuid}/status")
    public ResponseDto<PartnerStatusDto> getPartnerStatus(@PathVariable("partner-uuid")String partnerUUID){

        return null;
    }

    @PostMapping("/{partner-uuid}/status")
    public ResponseDto<PartnerStatusDto> savePartnerStatus(@PathVariable("partner-uuid")String partnerUUID, @RequestBody ActionDto actionDto){

        return null;
    }

}

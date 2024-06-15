package com.goev.partner.controller;

import com.goev.lib.dto.ResponseDto;
import com.goev.lib.dto.StatusDto;
import com.goev.partner.dto.common.PageDto;
import com.goev.partner.dto.common.PaginatedResponseDto;
import com.goev.partner.dto.partner.duty.PartnerDutyDto;
import com.goev.partner.service.duty.PartnerDutyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/partner-management")
@AllArgsConstructor
public class DutyController {

    private final PartnerDutyService partnerDutyService;
    @GetMapping("/partners/{partner-uuid}/duties")
    public ResponseDto<PaginatedResponseDto<PartnerDutyDto>> getDutiesForPartner(@PathVariable("partner-uuid")String partnerUUID,
                                                                                 PageDto page){
        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(),200, partnerDutyService.getDutiesForPartner(partnerUUID,page));
    }

    @GetMapping("/partners/{partner-uuid}/duties/{partner-duty-uuid}")
    public ResponseDto<PartnerDutyDto> getDutyDetailsForPartner(@PathVariable("partner-uuid")String partnerUUID,
                                                                                 @PathVariable("partner-duty-uuid") String dutyUUID){
        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(),200, partnerDutyService.getDutyDetails(partnerUUID,dutyUUID));
    }
}

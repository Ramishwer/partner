package com.goev.partner.controller;

import com.goev.lib.dto.ResponseDto;
import com.goev.partner.dto.common.PaginatedResponseDto;
import com.goev.partner.dto.partner.attendance.PartnerDutyDto;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/partner-management")
public class DutyController {

    @GetMapping("/{partner-uuid}/duties")
    public ResponseDto<PaginatedResponseDto<PartnerDutyDto>> getDutiesForPartner(@PathVariable("partner-uuid")String partnerUUID,
                                                                                 @RequestParam("count")Integer count,
                                                                                 @RequestParam("start")Integer start){
        return null;
    }
}

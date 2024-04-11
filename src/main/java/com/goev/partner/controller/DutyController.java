package com.goev.partner.controller;

import com.goev.lib.dto.ResponseDto;
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
    public ResponseDto<List<PartnerDutyDto>> getDutiesForPartner(@PathVariable("partner-uuid")String partnerUUID,
                                                                 @RequestParam("from") DateTime from,
                                                                 @RequestParam("to")DateTime to,
                                                                 @RequestParam("count")Integer count,
                                                                 @RequestParam("start")Integer start){
        return null;
    }
}

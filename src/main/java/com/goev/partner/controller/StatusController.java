package com.goev.partner.controller;

import com.goev.lib.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/partner-management")
public class StatusController {

    @GetMapping("/{partner-uuid}/status")
    public ResponseDto getPartnerStatus(@PathVariable("partner-uuid")String partnerUUID){

        return null;
    }

}

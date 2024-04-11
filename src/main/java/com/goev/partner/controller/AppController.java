package com.goev.partner.controller;

import com.goev.lib.dto.ResponseDto;
import com.goev.lib.dto.StatusDto;
import com.goev.partner.dto.partner.app.AppPropertyDto;
import com.goev.partner.service.partner.PartnerAppService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/partner-management")
public class AppController {

    private final PartnerAppService partnerAppService;

    @GetMapping("/app-properties")
    public ResponseDto<AppPropertyDto> getAppProperties(){
        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(),200, partnerAppService.getAppProperties());
    }


}

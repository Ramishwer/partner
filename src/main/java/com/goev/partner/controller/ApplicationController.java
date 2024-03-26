package com.goev.partner.controller;

import com.goev.lib.dto.ResponseDto;
import com.goev.lib.dto.StatusDto;
import com.goev.partner.dto.application.ApplicationPropertiesDto;
import com.goev.partner.dto.session.SessionDto;
import com.goev.partner.service.ApplicationPropertiesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/application-management")
public class ApplicationController {

    @Autowired
    private ApplicationPropertiesService applicationPropertiesService;

    @GetMapping("/application/properties")
    public ResponseDto<ApplicationPropertiesDto> getApplicationProperties(){
        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(),200, applicationPropertiesService.getApplicationProperties());
    }
}

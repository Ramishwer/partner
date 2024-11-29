package com.goev.partner.controller;

import com.goev.lib.dto.ResponseDto;
import com.goev.lib.dto.StatusDto;
import com.goev.partner.dto.partner.detail.PartnerAccountDto;
import com.goev.partner.service.partner.PartnerAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/partner-management")
@AllArgsConstructor
public class PartnerAccountController {

    private final PartnerAccountService partnerAccountService;

    @PostMapping("/partners/{partner-uuid}/accounts")
    public ResponseDto<PartnerAccountDto> createAccount(@PathVariable(value = "partner-uuid") String partnerUUID, @RequestBody PartnerAccountDto partnerAccountDto) {
        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(), 200, partnerAccountService.createAccount(partnerUUID, partnerAccountDto));
    }
}

package com.goev.partner.controller;


import com.goev.lib.dto.ResponseDto;
import com.goev.lib.dto.StatusDto;
import com.goev.partner.dao.earning.EarningRuleDao;
import com.goev.partner.service.earning.EarningRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/earning-rules")
public class EarningRuleController {

    private final EarningRuleService earningRuleService;

    @Autowired
    public EarningRuleController(EarningRuleService earningRuleService) {
        this.earningRuleService = earningRuleService;
    }


    @GetMapping("/find")
    public ResponseDto<List<EarningRuleDao>> getEarningRulesByClientName(@RequestParam("clientName") String clientName) {
        List<EarningRuleDao> earningRules = earningRuleService.getEarningRulesByClientName(clientName);
        return new ResponseDto<>(
                StatusDto.builder().message("SUCCESS").build(),
                200,
                earningRules
        );
    }
}
package com.goev.partner.controller;

import com.goev.lib.dto.ResponseDto;
import com.goev.lib.dto.StatusDto;
import com.goev.partner.dto.common.PaginatedResponseDto;
import com.goev.partner.dto.partner.payout.PartnerPayoutDto;
import com.goev.partner.dto.partner.payout.PartnerPayoutSummaryDto;
import com.goev.partner.dto.partner.payout.PartnerPayoutTransactionDto;
import com.goev.partner.service.payout.PartnerPayoutService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/partner-management")
@AllArgsConstructor
public class PayoutController {

    private final PartnerPayoutService partnerPayoutService;
    @GetMapping("/partners/{partner-uuid}/payouts")
    public ResponseDto<PaginatedResponseDto<PartnerPayoutDto>> getPayoutsForPartner(@PathVariable("partner-uuid")String partnerUUID,
                                                                                    @RequestParam("count")Integer count,
                                                                                    @RequestParam("start")Integer start){
        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(),200, new PaginatedResponseDto<>());
    }

    @GetMapping("/partners/{partner-uuid}/payouts/{partner-payout-uuid}/summary")
    public ResponseDto<PartnerPayoutSummaryDto> getPayoutSummaryForPartnerAndPayout(@PathVariable("partner-uuid")String partnerUUID,
                                                                                    @PathVariable("partner-payout-uuid")String partnerPayoutUUID){
        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(),200, new PartnerPayoutSummaryDto());
    }

    @GetMapping("/partners/{partner-uuid}/payouts/{partner-payout-uuid}/transactions")
    public ResponseDto<PaginatedResponseDto<PartnerPayoutTransactionDto>> getPayoutTransactionsForPartnerAndPayout(@PathVariable("partner-uuid")String partnerUUID,
                                                                                             @PathVariable("partner-payout-uuid")String partnerPayoutUUID){
        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(),200, new PaginatedResponseDto<>());
    }

}

package com.goev.partner.controller;

import com.goev.lib.dto.ResponseDto;
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
    @GetMapping("/{partner-uuid}/payouts")
    public ResponseDto<PaginatedResponseDto<PartnerPayoutDto>> getPayoutsForPartner(@PathVariable("partner-uuid")String partnerUUID,
                                                                                    @RequestParam("count")Integer count,
                                                                                    @RequestParam("start")Integer start){
        return null;
    }

    @GetMapping("/{partner-uuid}/payouts/{payout-uuid}/summary")
    public ResponseDto<PartnerPayoutSummaryDto> getPayoutSummaryForPartnerAndPayout(@PathVariable("partner-uuid")String partnerUUID,
                                                                                    @PathVariable("payout-uuid")String payoutUUID){
        return null;
    }

    @GetMapping("/{partner-uuid}/payouts/{payout-uuid}/transactions")
    public ResponseDto<PartnerPayoutTransactionDto> getPayoutTransactionsForPartnerAndPayout(@PathVariable("partner-uuid")String partnerUUID,
                                                                                             @PathVariable("payout-uuid")String payoutUUID){
        return null;
    }

}

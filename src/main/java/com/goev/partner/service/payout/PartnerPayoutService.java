package com.goev.partner.service.payout;

import com.goev.partner.dto.common.PageDto;
import com.goev.partner.dto.common.PaginatedResponseDto;
import com.goev.partner.dto.partner.payout.PartnerPayoutDto;
import com.goev.partner.dto.partner.payout.PartnerPayoutSummaryDto;
import com.goev.partner.dto.partner.payout.PartnerPayoutTransactionDto;

public interface PartnerPayoutService {
    PaginatedResponseDto<PartnerPayoutDto> getPayouts(String partnerUUID ,PageDto page);

    PartnerPayoutSummaryDto getPayoutSummaryForPayoutUUID(String partnerUUID, String partnerPayoutUUID);

    PaginatedResponseDto<PartnerPayoutTransactionDto> getPayoutTransactionsForPayoutUUID(String partnerUUID, String partnerPayoutUUID, PageDto page);
}

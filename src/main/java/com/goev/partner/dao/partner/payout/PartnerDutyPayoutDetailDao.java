package com.goev.partner.dao.partner.payout;

import com.goev.lib.dao.BaseDao;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PartnerDutyPayoutDetailDao extends BaseDao {
    private Integer partnerId;
    private Integer partnerPayoutId;
    private Integer partnerDutyId;
    private Integer plannedAmount;
    private Integer actualAmount;
    private Integer plannedDeductionAmount;
    private Integer actualDeductionAmount;
    private String actionDetails;
    private Integer creditAmount;
    private Integer debitAmount;
    private Integer payoutModelId;
}



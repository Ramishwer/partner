package com.goev.partner.dao.partner.payout;

import com.goev.lib.dao.BaseDao;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PartnerPayoutModelElementMappingDao extends BaseDao {
    private Integer partnerPayoutElementId;
    private Integer partnerPayoutModelId;
}



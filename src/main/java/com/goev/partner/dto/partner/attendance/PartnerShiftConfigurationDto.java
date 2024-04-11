package com.goev.partner.dto.partner.attendance;

import com.goev.partner.dto.partner.payout.PartnerPayoutModelDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PartnerShiftConfigurationDto {
    private String uuid;
    private String shiftStart;
    private String shiftEnd;
    private PartnerPayoutModelDto payoutModel;
}

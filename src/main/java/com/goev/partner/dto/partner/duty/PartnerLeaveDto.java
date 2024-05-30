package com.goev.partner.dto.partner.duty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.goev.partner.dto.partner.PartnerViewDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PartnerLeaveDto {
    private String uuid;
    private PartnerViewDto partner;
}

package com.goev.partner.dto.partner.detail;

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
public class PartnerActionDto {
    private String actionDetails;
    private String action;
    private PartnerViewDto partner;
    private String status;
}

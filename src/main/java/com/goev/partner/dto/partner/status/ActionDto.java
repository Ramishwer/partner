package com.goev.partner.dto.partner.status;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.goev.lib.dto.LatLongDto;
import com.goev.partner.enums.partner.PartnerAction;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActionDto {
    protected PartnerAction action;
    protected LatLongDto location;
    protected String qrString;
}

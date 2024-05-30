package com.goev.partner.dto.partner.status;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.goev.lib.dto.LatLongDto;
import com.goev.partner.dto.location.LocationDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActionDto {
    protected String action;
    protected Object data;
    protected LatLongDto location;
    private String qrString;
    private String validationType;
}

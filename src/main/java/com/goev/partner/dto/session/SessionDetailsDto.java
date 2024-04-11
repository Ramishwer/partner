package com.goev.partner.dto.session;

import com.goev.lib.dto.LatLongDto;
import com.goev.partner.dto.partner.detail.PartnerDeviceDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SessionDetailsDto {
    private SessionDto details;
    private PartnerDeviceDto deviceDetails;
    private LatLongDto location;
    private String uuid;
}

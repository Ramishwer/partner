package com.goev.partner.dto.session;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.goev.lib.dto.OtpCredentialsDto;
import com.goev.partner.dto.partner.detail.PartnerDeviceDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SessionRequestDto {
    private OtpCredentialsDto credentials;
    private PartnerDeviceDto deviceDetails;
}

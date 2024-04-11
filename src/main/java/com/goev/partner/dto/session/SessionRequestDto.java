package com.goev.partner.dto.session;

import com.goev.lib.dto.OtpCredentialsDto;
import com.goev.partner.dto.partner.detail.PartnerDeviceDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SessionRequestDto {
    private OtpCredentialsDto credentials;
    private PartnerDeviceDto deviceDetais;
}

package com.goev.partner.dto.auth;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AuthClientDto {
    private String clientKey;
    private String uuid;
    private Boolean isUserRegistrationAllowed;
    private String organizationUUID;
}

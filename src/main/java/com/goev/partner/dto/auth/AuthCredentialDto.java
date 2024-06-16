package com.goev.partner.dto.auth;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AuthCredentialDto {
    private String authKey;
    private String authSecret;
    private AuthCredentialTypeDto authCredentialType;
    private String authUUID;
}

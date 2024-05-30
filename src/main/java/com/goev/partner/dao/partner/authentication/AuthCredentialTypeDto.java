package com.goev.partner.dao.partner.authentication;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AuthCredentialTypeDto {
    private String name;
    private String uuid;
}


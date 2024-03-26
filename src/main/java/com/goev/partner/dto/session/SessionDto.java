package com.goev.partner.dto.session;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SessionDto {
    private String accessToken;
    private String refreshToken;
    private Long expiresIn;
    private String uuid;
    private String authId;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private String state;
}

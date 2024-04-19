package com.goev.partner.dto.session;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SessionDto {
    private String accessToken;
    private String refreshToken;
    private Integer expiresIn;
    private String uuid;
    private String partnerUUID;
    private String authUUID;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private String state;
    private String organizationUUID;
}

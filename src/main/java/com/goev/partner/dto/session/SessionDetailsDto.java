package com.goev.partner.dto.session;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SessionDetailsDto {
    private SessionDto details;
    private String uuid;
}

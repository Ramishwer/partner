package com.goev.partner.dto.application;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class KeyDto {
    private String signature;
    private String encryption;
    private String payload;
}

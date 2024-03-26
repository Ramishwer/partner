package com.goev.partner.dto.application;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ApplicationPropertiesDto {
    private String currentAppVersion;
    private String minimumAppVersion;
    private KeyDto key;
}

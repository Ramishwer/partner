package com.goev.partner.dto.partner.app;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AppPropertyDto {
    private String currentAppVersion;
    private String minimumAppVersion;
    private KeyDto key;
    private List<AppSupportedLanguageDto> supportedLanguages;
}

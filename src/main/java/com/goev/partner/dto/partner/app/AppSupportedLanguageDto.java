package com.goev.partner.dto.partner.app;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AppSupportedLanguageDto {
    private String code;
    private String name;
    private String url;
}

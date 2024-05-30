package com.goev.partner.dto.partner.app;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppSupportedLanguageDto {
    private String languageCode;
    private String name;
    private String url;
}

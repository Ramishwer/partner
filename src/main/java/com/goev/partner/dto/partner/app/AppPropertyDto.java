package com.goev.partner.dto.partner.app;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppPropertyDto {
    private String currentAppVersion;
    private String minimumAppVersion;
    private KeyDto key;
    private List<AppSupportedLanguageDto> supportedLanguages;
    private SupportDetailDto supportDetails;
}

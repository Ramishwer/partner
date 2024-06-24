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
public class SupportDetailDto {
    private String supportNumber;
    private String supportEmail;
    private String supportAddress;
}

package com.goev.partner.dto.common;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AttributeDto {
    private String key;
    private String value;
    private String type;
}

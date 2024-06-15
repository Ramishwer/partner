package com.goev.partner.dto.partner.document;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PartnerDocumentTypeDto {
    private String uuid;
    private String name;
    private String s3Key;
    private String label;
    private String groupKey;
    private String groupDescription;
    private Boolean needsVerification;
    private Boolean isMandatory;
    private String icon;
    private String fileTypes;
}

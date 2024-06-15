package com.goev.partner.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.joda.time.DateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageDto {
    private String lastElementUUID;
    private Integer start;
    private Integer limit;
}

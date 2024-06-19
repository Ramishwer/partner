package com.goev.partner.dto.shift;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShiftConfigurationDto {
    private String uuid;
    private ShiftDto shift;
    private String estimatedIn;
    private String estimatedOut;
    private String day;
    private String minimumIn;
    private String maximumIn;
    private String minimumOut;
    private String maximumOut;
}




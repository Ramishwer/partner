package com.goev.partner.dto.partner.status;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.goev.partner.dto.location.LocationDto;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PartnerStatusDto {
    private String status;
    private String subStatus;
    private List<ActionDto> nextActions;
    private LocationDto location;
    private String onboardingStatus;

}

package com.goev.partner.dto.partner.status;

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
public class PartnerStatusDto {
    private String status; // VERIFICATION_PENDING->LOGGED_IN->CHECKED_IN->VEHICLE_ASSIGNED->
    private List<ActionDto> nextActions;
}

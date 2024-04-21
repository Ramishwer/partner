package com.goev.partner.dto.partner.status;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PartnerStatusDto {
    private String status; // VERIFICATION_PENDING->LOGGED_IN->CHECKED_IN->VEHICLE_ASSIGNED->
    private List<ActionDto> nextActions;
}

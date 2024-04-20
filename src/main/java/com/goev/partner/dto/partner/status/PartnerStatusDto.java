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
    private String status;
    private List<ActionDto> nextActions;
}

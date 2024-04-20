package com.goev.partner.dto.booking;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BookingPricingDetailDto {
    private String uuid;
    private Integer estimatedAmount;
    private Integer actualAmount;
}

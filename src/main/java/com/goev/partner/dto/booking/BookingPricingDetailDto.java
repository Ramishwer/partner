package com.goev.partner.dto.booking;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingPricingDetailDto {
    private String uuid;
    private Integer estimatedAmount;
    private Integer actualAmount;
}

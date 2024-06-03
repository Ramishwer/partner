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
    private BookingViewDto booking;
    private Integer plannedTotalAmount;
    private Integer plannedTotalAmountExcludingTax;
    private Integer plannedTaxAmount;
    private Integer plannedTaxPercentage;
    private Integer actualTotalAmount;
    private Integer actualTotalAmountExcludingTax;
    private Integer actualTaxAmount;
    private Integer actualTaxPercentage;
    private String status;
}

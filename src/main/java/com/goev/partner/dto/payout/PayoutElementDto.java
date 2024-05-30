package com.goev.partner.dto.payout;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PayoutElementDto {
    private String name;
    private String type;
    private String description;
    private String rules;
    private String title;
    private String subtitle;
    private String message;
    private String category;
    private Integer sortingOrder;
    private String uuid;
}

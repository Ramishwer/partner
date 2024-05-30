package com.goev.partner.dto.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerViewDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String uuid;
}

package com.goev.partner.dto.partner.status;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ActionDto {
    protected String action;
    protected Object data;
}

package com.goev.partner.dto.partner.status;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.goev.partner.dto.location.LocationDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActionDto {
    protected String action;//UPLOAD_DOCUMENT,CHECKIN,ASSIGN_VEHICLE,
    protected Object data;
    protected LocationDto location;
}

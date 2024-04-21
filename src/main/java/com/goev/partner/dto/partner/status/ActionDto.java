package com.goev.partner.dto.partner.status;

import com.goev.partner.dto.location.LocationDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ActionDto {
    protected String action;//UPLOAD_DOCUMENT,CHECKIN,ASSIGN_VEHICLE,
    protected Object data;
    protected LocationDto location;
}

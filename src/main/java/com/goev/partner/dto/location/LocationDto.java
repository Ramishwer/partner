package com.goev.partner.dto.location;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.goev.lib.dto.LatLongDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocationDto {
    private String uuid;
    private String name;
    private String type;
    private LatLongDto coordinates;
    private String address;
    private OwnerDetailDto ownerDetail;
}

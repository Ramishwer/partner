package com.goev.partner.dto.vehicle.asset;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.goev.partner.dto.location.LocationDto;
import com.goev.partner.dto.vehicle.VehicleViewDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VehicleTransferDto {
    private String uuid;
    private VehicleViewDto vehicle;
    private String transferType;
    private TransferUserDetailsDto transferFrom;
    private TransferUserDetailsDto transferTo;
    private String status;
    private LocationDto transferLocationDetails;
    private Integer odometerReading;
    private Integer socReading;
}

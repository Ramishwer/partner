package com.goev.partner.dto.vehicle.asset;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.goev.partner.dto.location.LocationDto;
import com.goev.partner.dto.vehicle.detail.VehicleDto;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VehicleTransferDto {
    private String status;
    private String uuid;
    private TransferUserDetailsDto fromUUID;
    private TransferUserDetailsDto toUUID;
    private VehicleDto vehicleDetails;
    private LocationDto transferLocation;
    private String type;
    private String odometer;
    private String soc;
    private List<VehicleAssetTransferDetailDto> assets;
}

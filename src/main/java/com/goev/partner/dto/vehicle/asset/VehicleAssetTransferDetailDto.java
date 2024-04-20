package com.goev.partner.dto.vehicle.asset;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class VehicleAssetTransferDetailDto {
    private String uuid;
    private VehicleAssetDto asset;
    private String status;
}

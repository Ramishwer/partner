package com.goev.partner.service.vehicle;

import com.goev.partner.dto.common.PaginatedResponseDto;
import com.goev.partner.dto.vehicle.asset.VehicleAssetTransferDetailDto;
import com.goev.partner.dto.vehicle.asset.VehicleTransferDto;

public interface VehicleTransferService {

    VehicleTransferDto createVehicleTransfer(String vehicleUUID, VehicleTransferDto vehicleTransferDto);

    PaginatedResponseDto<VehicleAssetTransferDetailDto> getVehicleAssetsForTransfer(String vehicleUUID, String vehicleTransferUUID);

    VehicleTransferDto getVehicleTransferDetails(String vehicleUUID, String vehicleTransferUUID);

    VehicleTransferDto updateVehicleTransfer(String vehicleUUID, String vehicleTransferUUID, VehicleTransferDto vehicleTransferDto);

    VehicleAssetTransferDetailDto saveStatusForAssetForQrString(String vehicleUUID, String vehicleTransferUUID, String assetUUID, String status, String assetQrString);
}

package com.goev.partner.service.vehicle;

import com.goev.partner.dto.common.PaginatedResponseDto;
import com.goev.partner.dto.vehicle.asset.VehicleAssetDto;
import com.goev.partner.dto.vehicle.asset.VehicleTransferDto;

public interface VehicleTransferService {

    VehicleTransferDto createVehicleTransfer(String vehicleUUID, VehicleTransferDto vehicleTransferDto);

    PaginatedResponseDto<VehicleAssetDto> getVehicleAssetsForTransfer(String vehicleUUID, String vehicleTransferUUID);

    VehicleTransferDto getVehicleTransferDetails(String vehicleUUID, String vehicleTransferUUID, VehicleTransferDto vehicleTransferDto);

    VehicleTransferDto updateVehicleTransfer(String vehicleUUID, String vehicleTransferUUID, VehicleTransferDto vehicleTransferDto);

    VehicleAssetDto saveStatusForAssetForQrString(String vehicleUUID, String vehicleTransferUUID, String assetUUID, String assetQrString);
}

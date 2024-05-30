package com.goev.partner.service.vehicle.impl;


import com.goev.partner.dto.common.PaginatedResponseDto;
import com.goev.partner.dto.vehicle.asset.VehicleAssetDto;
import com.goev.partner.dto.vehicle.asset.VehicleTransferDto;
import com.goev.partner.repository.vehicle.detail.VehicleRepository;
import com.goev.partner.service.vehicle.VehicleTransferService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@AllArgsConstructor
public class VehicleTransferServiceImpl implements VehicleTransferService {

    private final VehicleRepository vehicleRepository;


    @Override
    public VehicleTransferDto createVehicleTransfer(String vehicleUUID, VehicleTransferDto vehicleTransferDto) {
        return null;
    }

    @Override
    public PaginatedResponseDto<VehicleAssetDto> getVehicleAssetsForTransfer(String vehicleUUID, String vehicleTransferUUID) {
        return null;
    }

    @Override
    public VehicleTransferDto getVehicleTransferDetails(String vehicleUUID, String vehicleTransferUUID, VehicleTransferDto vehicleTransferDto) {
        return null;
    }

    @Override
    public VehicleTransferDto updateVehicleTransfer(String vehicleUUID, String vehicleTransferUUID, VehicleTransferDto vehicleTransferDto) {
        return null;
    }

    @Override
    public VehicleAssetDto saveStatusForAssetForQrString(String vehicleUUID, String vehicleTransferUUID, String assetUUID, String assetQrString) {
        return null;
    }
}

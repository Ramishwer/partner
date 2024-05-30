package com.goev.partner.controller;

import com.goev.lib.dto.ResponseDto;
import com.goev.lib.dto.StatusDto;
import com.goev.partner.dto.common.PaginatedResponseDto;
import com.goev.partner.dto.vehicle.asset.VehicleAssetDto;
import com.goev.partner.dto.vehicle.asset.VehicleTransferDto;
import com.goev.partner.dto.vehicle.detail.VehicleDto;
import com.goev.partner.service.vehicle.VehicleAssetService;
import com.goev.partner.service.vehicle.VehicleService;
import com.goev.partner.service.vehicle.VehicleTransferService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/vehicle-management/")
@AllArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;
    private final VehicleTransferService vehicleTransferService;

    @GetMapping("/vehicles/{vehicle-uuid}")
    public ResponseDto<VehicleDto> getVehicleDetails(@PathVariable("vehicle-uuid") String vehicleUUID) {
        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(),200,vehicleService.getVehicleDetails(vehicleUUID));
    }


    @GetMapping("/vehicles/{vehicle-uuid}/transfers/{vehicle-transfer-uuid}/assets")
    public ResponseDto<PaginatedResponseDto<VehicleAssetDto>> getVehicleAssets(@PathVariable("vehicle-uuid") String vehicleUUID,
                                                                               @PathVariable("vehicle-transfer-uuid") String vehicleTransferUUID) {
        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(),200, vehicleTransferService.getVehicleAssetsForTransfer(vehicleUUID,vehicleTransferUUID));
    }

    @PostMapping("/vehicles/{vehicle-uuid}/transfers/{vehicle-transfer-uuid}/assets")
    public ResponseDto<VehicleAssetDto> saveVehicleAssetStatus(@PathVariable("vehicle-uuid") String vehicleUUID,
                                                               @PathVariable("vehicle-transfer-uuid") String vehicleTransferUUID,
                                                               @PathVariable("asset-uuid") String assetUUID,
                                                               @RequestBody String assetQrString) {
        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(),200, vehicleTransferService.saveStatusForAssetForQrString(vehicleUUID,vehicleTransferUUID,assetUUID,assetQrString));
    }

    @GetMapping("/vehicles/{vehicle-uuid}/transfers/{vehicle-transfer-uuid}")
    public ResponseDto<VehicleTransferDto> getVehicleTransferDetails(@PathVariable("vehicle-uuid") String vehicleUUID,
                                                                     @PathVariable("vehicle-transfer-uuid") String vehicleTransferUUID,
                                                                     @RequestBody VehicleTransferDto vehicleTransferDto) {
        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(),200, vehicleTransferService.getVehicleTransferDetails(vehicleUUID,vehicleTransferUUID,vehicleTransferDto));
    }

    @PostMapping("/vehicles/{vehicle-uuid}/transfers")
    public ResponseDto<VehicleTransferDto> saveVehicleTransfer(@PathVariable("vehicle-uuid") String vehicleUUID,
                                                               @RequestBody VehicleTransferDto vehicleTransferDto) {
        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(),200, vehicleTransferService.createVehicleTransfer(vehicleUUID,vehicleTransferDto));
    }
    @PutMapping("/vehicles/{vehicle-uuid}/transfers/{vehicle-transfer-uuid}")
    public ResponseDto<VehicleTransferDto> updateVehicleTransfer(@PathVariable("vehicle-uuid") String vehicleUUID,
                                                                 @PathVariable("vehicle-transfer-uuid") String vehicleTransferUUID,
                                                                 @RequestBody VehicleTransferDto vehicleTransferDto) {
        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(),200, vehicleTransferService.updateVehicleTransfer(vehicleUUID,vehicleTransferUUID,vehicleTransferDto));
    }
}

package com.goev.partner.controller;

import com.goev.lib.dto.ResponseDto;
import com.goev.partner.dto.common.PaginatedResponseDto;
import com.goev.partner.dto.vehicle.asset.VehicleAssetDto;
import com.goev.partner.dto.vehicle.asset.VehicleTransferDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/vehicle-management/")
public class VehicleController {

    @GetMapping("/vehicles/{vehicle-uuid}")
    public ResponseDto<VehicleAssetDto> getVehicleDetails(@PathVariable("vehicle-uuid") String vehicleUUID) {
        return null;
    }

    @PostMapping("/vehicles/{vehicle-uuid}/transfers")
    public ResponseDto<VehicleTransferDto> saveVehicleTransfer(@PathVariable("vehicle-uuid") String vehicleUUID,
                                                               @RequestBody VehicleTransferDto vehicleTransferDto) {
        return null;
    }
    @GetMapping("/vehicles/{vehicle-uuid}/transfers/{vehicle-transfer-uuid}/assets")
    public ResponseDto<PaginatedResponseDto<VehicleAssetDto>> getVehicleAssets(@PathVariable("vehicle-uuid") String vehicleUUID,
                                                                               @PathVariable("vehicle-transfer-uuid") String vehicleTransferUUID) {
        return null;
    }

    @PostMapping("/vehicles/{vehicle-uuid}/transfers/{vehicle-transfer-uuid}/assets")
    public ResponseDto<VehicleAssetDto> saveVehicleAssetStatus(@PathVariable("vehicle-uuid") String vehicleUUID,
                                                               @PathVariable("vehicle-transfer-uuid") String vehicleTransferUUID,
                                                               @PathVariable("asset-uuid") String assetUUID,
                                                               @RequestBody String assetQrString) {
        return null;
    }




    @GetMapping("/vehicles/{vehicle-uuid}/transfers/{vehicle-transfer-uuid}")
    public ResponseDto<VehicleTransferDto> getVehicleTransferDetails(@PathVariable("vehicle-uuid") String vehicleUUID,
                                                                     @PathVariable("vehicle-transfer-uuid") String vehicleTransferUUID,
                                                                     @RequestBody VehicleTransferDto vehicleTransferDto) {
        return null;
    }

    @PostMapping("/vehicles/{vehicle-uuid}/transfers/{vehicle-transfer-uuid}")
    public ResponseDto<VehicleTransferDto> updateVehicleTransfer(@PathVariable("vehicle-uuid") String vehicleUUID,
                                                                 @PathVariable("vehicle-transfer-uuid") String vehicleTransferUUID,
                                                                 @RequestBody VehicleTransferDto vehicleTransferDto) {
        return null;
    }
}

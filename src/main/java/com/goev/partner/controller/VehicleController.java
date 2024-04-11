package com.goev.partner.controller;

import com.goev.lib.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/partner-management/")
public class VehicleController {
    @GetMapping("/{partner-uuid}/vehicles/{vehicle-uuid}/assets")
    public ResponseDto getVehicleItems(@PathVariable("partner-uuid")String partnerUUID,
                                       @PathVariable("vehicle-uuid")String vehicleUUID){
        return null;
    }

    @PostMapping("/{partner-uuid}/vehicles/{vehicle-uuid}/assets/{asset-uuid}/status")
    public ResponseDto saveVehicleAssetStatus(@PathVariable("partner-uuid")String partnerUUID,
                                              @PathVariable("vehicle-uuid")String vehicleUUID,
                                              @PathVariable("asset-uuid")String assetUUID){
        return null;
    }
}

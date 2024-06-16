package com.goev.partner.dao.vehicle.transfer;

import com.goev.lib.dao.BaseDao;
import com.goev.partner.constant.ApplicationConstants;
import com.goev.partner.dto.vehicle.transfer.VehicleTransferDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class VehicleTransferDetailDao extends BaseDao {
    private Integer vehicleId;
    private String transferType;
    private String transferFrom;
    private String transferTo;
    private String status;
    private String transferDetails;
    private String transferLocationDetails;
    private Integer transferLocationId;
    private Integer odometerReading;
    private Integer socReading;

    public static VehicleTransferDetailDao fromDto(VehicleTransferDto vehicleTransferDto, Integer vehicleId) {
        VehicleTransferDetailDao result = new VehicleTransferDetailDao();
        result.setUuid(vehicleTransferDto.getUuid());
        result.setVehicleId(vehicleId);
        result.setTransferType(vehicleTransferDto.getTransferType());
        result.setTransferFrom(ApplicationConstants.GSON.toJson(vehicleTransferDto.getTransferFrom()));
        result.setTransferTo(ApplicationConstants.GSON.toJson(vehicleTransferDto.getTransferTo()));
        result.setStatus(vehicleTransferDto.getStatus());
        result.setTransferLocationDetails(ApplicationConstants.GSON.toJson(vehicleTransferDto.getTransferLocationDetails()));
        result.setOdometerReading(vehicleTransferDto.getOdometerReading());
        result.setSocReading(vehicleTransferDto.getSocReading());
        return result;
    }
}

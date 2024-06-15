package com.goev.partner.dto.vehicle.asset;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.goev.partner.constant.ApplicationConstants;
import com.goev.partner.dao.vehicle.asset.VehicleTransferDetailDao;
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

    public static VehicleTransferDto fromDao(VehicleTransferDetailDao detailDao, VehicleViewDto vehicleViewDto) {
        return VehicleTransferDto.builder()
                .odometerReading(detailDao.getOdometerReading())
                .vehicle(vehicleViewDto)
                .status(detailDao.getStatus())
                .socReading(detailDao.getSocReading())
                .transferFrom(ApplicationConstants.GSON.fromJson(detailDao.getTransferFrom(), TransferUserDetailsDto.class))
                .transferTo(ApplicationConstants.GSON.fromJson(detailDao.getTransferTo(), TransferUserDetailsDto.class))
                .transferLocationDetails(ApplicationConstants.GSON.fromJson(detailDao.getTransferLocationDetails(), LocationDto.class))
                .transferType(detailDao.getTransferType())
                .uuid(detailDao.getUuid()).build();
    }
}

package com.goev.partner.dao.vehicle.detail;

import com.goev.lib.dao.BaseDao;
import lombok.*;
import org.joda.time.DateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class VehicleDao extends BaseDao {
    private String plateNumber;
    private String status;
    private String partnerDetails;
    private String vehicleTransferDetails;
    private Integer soc;
    private Integer vehicleDetailsId;
    private String subStatus;
    private DateTime computedAvailableTime;
    private DateTime computedChargingTime;
    private String locationStatus;
    private Integer locationId;
    private String locationDetails;
    private Integer homeLocationId;
    private String homeLocationDetails;
    private String viewInfo;
    private String imageUrl;
    private String onboardingStatus;
    private String stats;
    private String segments;

}

package com.goev.partner.dao.vehicle.detail;

import com.goev.lib.dao.BaseDao;
import lombok.*;

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

}

package com.goev.partner.dao.vehicle.transfer;

import com.goev.lib.dao.BaseDao;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class VehicleAssetTransferDetailDao extends BaseDao {
    private Integer vehicleId;
    private Integer vehicleTransferId;
    private Integer assetId;
    private String status;
}

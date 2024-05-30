package com.goev.partner.dao.vehicle.asset;

import com.goev.lib.dao.BaseDao;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class VehicleAssetMappingDao extends BaseDao {
    private Integer vehicleId;
    private Integer assetId;
    private String status;
}

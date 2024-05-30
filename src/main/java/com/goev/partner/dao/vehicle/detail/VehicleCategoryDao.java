package com.goev.partner.dao.vehicle.detail;

import com.goev.lib.dao.BaseDao;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class VehicleCategoryDao extends BaseDao {
    private String name;
    private String description;
}

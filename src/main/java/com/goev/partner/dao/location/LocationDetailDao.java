package com.goev.partner.dao.location;

import com.goev.lib.dao.BaseDao;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class LocationDetailDao extends BaseDao {
    private Integer locationId;
    private String address;
    private String ownerDetails;
}





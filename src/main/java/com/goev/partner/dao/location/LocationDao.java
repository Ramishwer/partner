package com.goev.partner.dao.location;

import com.goev.lib.dao.BaseDao;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class LocationDao extends BaseDao {
    private String name;
    private String type;
    private Double latitude;
    private Double longitude;
    private Integer locationDetailsId;
}





package com.goev.partner.dao.vehicle.document;

import com.goev.lib.dao.BaseDao;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class VehicleDocumentTypeDao extends BaseDao {
    private String name;
    private String s3Key;
    private String label;
}

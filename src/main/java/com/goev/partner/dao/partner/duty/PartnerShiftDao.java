package com.goev.partner.dao.partner.duty;

import com.goev.lib.dao.BaseDao;
import lombok.*;
import org.joda.time.DateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PartnerShiftDao extends BaseDao {
    private String shiftStart;
    private String shiftEnd;
    private Integer payoutModelId;
    private DateTime applicableFromTime;
    private DateTime applicableToTime;
    private DateTime estimatedStartTime;
    private DateTime estimatedEndTime;
    private DateTime estimatedOnlineTime;
    private Integer partnerId;
    private String day;
    private Integer shiftId;
    private String shiftConfig;
    private Integer inLocationId;
    private Integer outLocationId;
    private Integer onlineLocationId;
    private Integer assignableVehicleCategoryId;
    private String type;
    private String dutyConfig;
    private DateTime dutyDate;
    private String inLocationDetails;
    private String outLocationDetails;
    private String onlineLocationDetails;
    private String assignableVehicleCategoryDetails;

}



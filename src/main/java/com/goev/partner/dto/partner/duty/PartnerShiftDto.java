package com.goev.partner.dto.partner.duty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.DateTimeDeserializer;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import com.goev.partner.dto.location.LocationDto;
import com.goev.partner.dto.partner.PartnerViewDto;
import com.goev.partner.dto.shift.ShiftConfigurationDto;
import com.goev.partner.dto.shift.ShiftDto;
import com.goev.partner.dto.vehicle.detail.VehicleCategoryDto;
import lombok.*;
import org.joda.time.DateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PartnerShiftDto {
    private String uuid;
    private String shiftStart;
    private String shiftEnd;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime applicableFromTime;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime applicableToTime;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime estimatedStartTime;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime estimatedEndTime;
    private PartnerViewDto partner;
    private String day;
    private ShiftDto shift;
    private ShiftConfigurationDto shiftConfig;
    private LocationDto inLocationDetails;
    private LocationDto outLocationDetails;
    private LocationDto onlineLocationDetails;
    private LocationDto offlineLocationDetails;
    private VehicleCategoryDto assignableVehicleCategoryDetails;
    private String type;
    private String status;
    private String subStatus;
    private String dutyConfig;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime dutyDate;

}

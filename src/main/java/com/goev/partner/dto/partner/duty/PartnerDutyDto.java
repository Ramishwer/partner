package com.goev.partner.dto.partner.duty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.DateTimeDeserializer;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import com.goev.partner.constant.ApplicationConstants;
import com.goev.partner.dao.partner.duty.PartnerDutyDao;
import com.goev.partner.dao.partner.duty.PartnerShiftDao;
import com.goev.partner.dto.location.LocationDto;
import com.goev.partner.dto.partner.PartnerViewDto;
import com.goev.partner.dto.shift.ShiftConfigurationDto;
import com.goev.partner.dto.vehicle.detail.VehicleCategoryDto;
import com.google.gson.reflect.TypeToken;
import lombok.*;
import org.joda.time.DateTime;

import java.lang.reflect.Type;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PartnerDutyDto {
    private String uuid;
    private PartnerViewDto partnerDetails;
    private PartnerShiftDto shiftDetails;
    private Long plannedTotalOnlineTimeInMillis;
    private Long plannedTotalPauseTimeInMillis;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime plannedDutyStartTime;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime plannedDutyEndTime;
    private LocationDto plannedDutyStartLocationDetails;
    private LocationDto plannedDutyEndLocationDetails;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime actualDutyStartTime;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime actualDutyEndTime;
    private LocationDto actualDutyStartLocationDetails;
    private LocationDto actualDutyEndLocationDetails;
    private Long actualTotalOnlineTimeInMillis;
    private Long actualTotalPauseTimeInMillis;
    private String status;
    private List<PartnerDutyVehicleDetailsDto> vehicles;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime maxOvertimeCalculationTime;

    public static PartnerDutyDto fromDao(PartnerDutyDao dutyDao, PartnerViewDto partner, PartnerShiftDao shift) {

        if(dutyDao==null)
            return null;

        PartnerDutyDto result = PartnerDutyDto.builder()
                .uuid(dutyDao.getUuid())
                .status(dutyDao.getStatus())
                .partnerDetails(partner)
                .plannedTotalOnlineTimeInMillis(dutyDao.getPlannedTotalOnlineTimeInMillis())
                .plannedTotalPauseTimeInMillis(dutyDao.getPlannedTotalPauseTimeInMillis())
                .plannedDutyStartTime(dutyDao.getPlannedDutyStartTime())
                .plannedDutyEndTime(dutyDao.getPlannedDutyEndTime())
                .plannedDutyStartLocationDetails(ApplicationConstants.GSON.fromJson(dutyDao.getPlannedDutyStartLocationDetails(), LocationDto.class))
                .actualTotalOnlineTimeInMillis(dutyDao.getActualTotalOnlineTimeInMillis())
                .actualTotalPauseTimeInMillis(dutyDao.getActualTotalPauseTimeInMillis())
                .actualDutyStartTime(dutyDao.getActualDutyStartTime())
                .actualDutyEndTime(dutyDao.getActualDutyEndTime())
                .actualDutyStartLocationDetails(ApplicationConstants.GSON.fromJson(dutyDao.getActualDutyStartLocationDetails(), LocationDto.class))
                .actualDutyEndLocationDetails(ApplicationConstants.GSON.fromJson(dutyDao.getActualDutyEndLocationDetails(), LocationDto.class))
                .maxOvertimeCalculationTime(dutyDao.getMaxOvertimeCalculationTime())
                .build();

        if (shift != null) {
            PartnerShiftDto shiftDto = PartnerShiftDto.fromDao(shift,partner);
            result.setShiftDetails(shiftDto);
        }

        if (dutyDao.getVehicles() != null) {
            Type t= new TypeToken<List<PartnerDutyVehicleDetailsDto>>(){}.getRawType();
            List<PartnerDutyVehicleDetailsDto> vehicles = ApplicationConstants.GSON.fromJson(dutyDao.getVehicles(),t);
            result.setVehicles(vehicles);
        }

        return result;

    }
}

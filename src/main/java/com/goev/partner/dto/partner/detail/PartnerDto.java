package com.goev.partner.dto.partner.detail;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.DateTimeDeserializer;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import com.goev.partner.constant.ApplicationConstants;
import com.goev.partner.dao.partner.detail.PartnerDao;
import com.goev.partner.dto.booking.BookingViewDto;
import com.goev.partner.dto.common.FileDto;
import com.goev.partner.dto.location.LocationDto;
import com.goev.partner.dto.partner.duty.PartnerDutyDto;
import com.goev.partner.dto.vehicle.VehicleViewDto;
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
public class PartnerDto {
    private String uuid;
    private String punchId;
    private String authUUID;
    private String phoneNumber;
    private String status;
    private String subStatus;
    private String profileUrl;
    private VehicleViewDto vehicleDetails;
    private BookingViewDto booking;
    private LocationDto locationDetails;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime computedAvailableTime;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime computedShiftEndTime;
    private PartnerDutyDto dutyDetails;
    private String locationStatus;
    private String onboardingStatus;
    private List<PartnerSegmentDto> segments;

    public static PartnerDto fromDao(PartnerDao partner) {
        if (partner == null)
            return null;
        PartnerDto result = PartnerDto.builder()
                .uuid(partner.getUuid())
                .punchId(partner.getPunchId())
                .phoneNumber(partner.getPhoneNumber())
                .status(partner.getStatus())
                .subStatus(partner.getSubStatus())
                .profileUrl(partner.getProfileUrl())
                .vehicleDetails(ApplicationConstants.GSON.fromJson(partner.getVehicleDetails(), VehicleViewDto.class))
                .booking(ApplicationConstants.GSON.fromJson(partner.getBookingDetails(), BookingViewDto.class))
                .locationDetails(ApplicationConstants.GSON.fromJson(partner.getLocationDetails(), LocationDto.class))
                .dutyDetails(ApplicationConstants.GSON.fromJson(partner.getDutyDetails(), PartnerDutyDto.class))
                .locationStatus(partner.getLocationStatus())
                .onboardingStatus(partner.getOnboardingStatus())
                .build();
        if (partner.getSegments() != null) {
            Type t = new TypeToken<List<PartnerSegmentDto>>() {
            }.getRawType();
            result.setSegments(ApplicationConstants.GSON.fromJson(partner.getSegments(), t));
        }
        return result;

    }


}

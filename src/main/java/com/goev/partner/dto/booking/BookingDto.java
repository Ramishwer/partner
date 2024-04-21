package com.goev.partner.dto.booking;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.DateTimeDeserializer;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import com.goev.partner.dto.location.LocationDto;
import com.goev.partner.dto.vehicle.detail.VehicleDto;
import lombok.*;
import org.joda.time.DateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDto {
    private String partnerUUID;
    private VehicleDto vehicle;
    private String customerUUID;
    private String status;
    private LocationDto startLocation;
    private LocationDto endLocation;
    private BookingTypeDto bookingType;
    private BookingPricingDetailDto pricingDetail;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime startTime;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime arriveTime;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime enrouteTime;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime endTime;
    private Integer arrivalToStartDistance;
    private Integer startToEndDistance;
    private Integer assignmentToEnrouteDistance;
    private Integer enrouteToArrivalDistance;
}

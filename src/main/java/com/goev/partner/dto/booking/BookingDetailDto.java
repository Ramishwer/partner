package com.goev.partner.dto.booking;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.DateTimeDeserializer;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import com.goev.lib.dto.LatLongDto;
import com.goev.partner.dto.customer.CustomerViewDto;
import com.goev.partner.dto.partner.PartnerViewDto;
import com.goev.partner.dto.vehicle.VehicleViewDto;
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
public class BookingDetailDto {
    private String uuid;
    private BookingViewDto booking;
    private CustomerViewDto customer;
    private PartnerViewDto partner;
    private VehicleViewDto vehicle;
    private BookingTypeDto bookingType;
    private BookingPricingDetailDto bookingPricingDetail;
    private VehicleCategoryDto requestedVehicleCategory;
    private String startGeohash;
    private String endGeohash;
    private String startRegions;
    private String endRegions;
    private Integer plannedAmount;
    private Integer actualAmount;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime plannedAssignmentTime;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime plannedEnrouteTime;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime plannedArrivalTime;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime plannedStartTime;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime plannedEndTime;
    private Integer plannedAssignmentToEnrouteDistance;
    private Integer plannedEnrouteToArrivalDistance;
    private Integer plannedArrivalToStartDistance;
    private Integer plannedStartToEndDistance;
    private LatLongDto plannedAssignmentLocationDetails;
    private LatLongDto plannedEnrouteLocationDetails;
    private LatLongDto plannedArrivalLocationDetails;
    private LatLongDto plannedStartLocationDetails;
    private LatLongDto plannedEndLocationDetails;
    private Integer plannedAssignmentSoc;
    private Integer plannedEnrouteSoc;
    private Integer plannedArrivalSoc;
    private Integer plannedStartSoc;
    private Integer plannedEndSoc;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime actualAssignmentTime;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime actualEnrouteTime;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime actualArrivalTime;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime actualStartTime;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime actualEndTime;
    private Integer actualAssignmentToEnrouteDistance;
    private Integer actualEnrouteToArrivalDistance;
    private Integer actualArrivalToStartDistance;
    private Integer actualStartToEndDistance;
    private LatLongDto actualAssignmentLocationDetails;
    private LatLongDto actualEnrouteLocationDetails;
    private LatLongDto actualArrivalLocationDetails;
    private LatLongDto actualStartLocationDetails;
    private LatLongDto actualEndLocationDetails;
    private Integer actualAssignmentSoc;
    private Integer actualEnrouteSoc;
    private Integer actualArrivalSoc;
    private Integer actualStartSoc;
    private Integer actualEndSoc;
    private String platform;
    private String version;
    private String deviceUUID;
    private String sessionUUID;
}

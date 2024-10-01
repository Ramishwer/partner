package com.goev.partner.dto.vehicle.detail;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.DateTimeDeserializer;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import com.goev.partner.dto.location.LocationDto;
import com.goev.partner.dto.partner.PartnerViewDto;
import com.goev.partner.dto.vehicle.VehicleViewDto;
import com.goev.partner.dto.vehicle.transfer.VehicleTransferDto;
import lombok.*;
import org.joda.time.DateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VehicleDto {
    private String imageUrl;
    private String uuid;
    private String plateNumber;
    private String status;
    private PartnerViewDto partnerDetails;
    private VehicleTransferDto vehicleTransferDetails;
    private Integer soc;
    private Integer dte;
    private String subStatus;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime computedAvailableTime;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime computedChargingTime;
    private String locationStatus;
    private LocationDto locationDetails;
    private String onboardingStatus;
    private VehicleViewDto vehicleDetails;
}

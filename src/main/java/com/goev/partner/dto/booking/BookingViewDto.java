package com.goev.partner.dto.booking;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.DateTimeDeserializer;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import com.goev.lib.dto.ContactDetailsDto;
import com.goev.lib.dto.LatLongDto;
import com.goev.partner.constant.ApplicationConstants;
import com.goev.partner.dao.booking.BookingDao;
import com.goev.partner.dto.business.BusinessClientDto;
import com.goev.partner.dto.business.BusinessSegmentDto;
import com.goev.partner.dto.customer.CustomerViewDto;
import com.goev.partner.dto.partner.PartnerViewDto;
import com.goev.partner.dto.vehicle.VehicleViewDto;
import com.goev.partner.dto.vehicle.detail.VehicleCategoryDto;
import lombok.*;
import org.joda.time.DateTime;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingViewDto {
    private String uuid;
    private String displayCode;
    private String status;
    private String subStatus;
    private SchedulingDetailDto schedulingDetails;
    private BookingTypeDto bookingTypeDetails;
    private LatLongDto startLocationDetails;
    private LatLongDto endLocationDetails;
    private PartnerViewDto partnerDetails;
    private VehicleViewDto vehicleDetails;
    private CustomerViewDto customerDetails;
    private BookingPricingDetailDto pricingDetails;
    private BookingPaymentDto paymentDetails;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime actualStartTime;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime actualEndTime;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime plannedStartTime;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime plannedEndTime;
    private Long distance;
    private Long duration;
    private ContactDetailsDto startContact;
    private ContactDetailsDto endContact;
    private BusinessClientDto businessClient;
    private BusinessSegmentDto businessSegment;
    private VehicleCategoryDto requestedVehicleCategory;
    private Long timeToReach;
    private Long distanceToReach;
    private String remark;
    private Map<String,Object> fields;



    public static BookingViewDto fromDao(BookingDao bookingDao) {
        if (bookingDao.getViewInfo() == null)
            return null;
        BookingViewDto result = ApplicationConstants.GSON.fromJson(bookingDao.getViewInfo(), BookingViewDto.class);
        result.setUuid(bookingDao.getUuid());
        return result;
    }
}

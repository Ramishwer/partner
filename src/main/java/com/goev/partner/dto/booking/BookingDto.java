package com.goev.partner.dto.booking;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.goev.lib.dto.LatLongDto;
import com.goev.partner.dao.booking.BookingDao;
import com.goev.partner.dto.customer.CustomerViewDto;
import com.goev.partner.dto.partner.PartnerViewDto;
import com.goev.partner.dto.vehicle.VehicleViewDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDto {
    private String uuid;
    private BookingTypeDto bookingTypeDetails;
    private String status;
    private String subStatus;
    private LatLongDto startLocationDetails;
    private LatLongDto endLocationDetails;
    private PartnerViewDto partnerDetails;
    private VehicleViewDto vehicleDetails;
    private CustomerViewDto customerDetails;

    public static BookingDto fromDao(BookingDao bookingDao) {
        return BookingDto.builder().uuid(bookingDao.getUuid()).build();
    }
}

package com.goev.partner.service.booking;

import com.goev.partner.dto.booking.BookingDto;
import com.goev.partner.dto.common.PaginatedResponseDto;

public interface PartnerBookingService {
    PaginatedResponseDto<BookingDto> getBookings(String partnerUUID, Integer count, Integer start);

    BookingDto getBookingDetails(String partnerUUID, String bookingUUID);
}

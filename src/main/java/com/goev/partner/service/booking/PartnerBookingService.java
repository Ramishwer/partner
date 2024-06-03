package com.goev.partner.service.booking;

import com.goev.partner.dto.booking.BookingViewDto;
import com.goev.partner.dto.common.PaginatedResponseDto;

public interface PartnerBookingService {
    PaginatedResponseDto<BookingViewDto> getBookings(String partnerUUID, Integer count, Integer start);

    BookingViewDto getBookingDetails(String partnerUUID, String bookingUUID);
}

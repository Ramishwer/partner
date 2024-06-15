package com.goev.partner.service.booking;

import com.goev.partner.dto.booking.BookingViewDto;
import com.goev.partner.dto.common.PageDto;
import com.goev.partner.dto.common.PaginatedResponseDto;

public interface PartnerBookingService {
    PaginatedResponseDto<BookingViewDto> getBookings(String partnerUUID, PageDto page);

    BookingViewDto getBookingDetails(String partnerUUID, String bookingUUID);
}

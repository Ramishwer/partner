package com.goev.partner.service.booking.impl;

import com.goev.partner.dto.booking.BookingDto;
import com.goev.partner.dto.common.PaginatedResponseDto;
import com.goev.partner.service.booking.PartnerBookingService;
import com.goev.partner.service.partner.PartnerDocumentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class PartnerBookingServiceImpl implements PartnerBookingService {
    @Override
    public PaginatedResponseDto<BookingDto> getBookings(String partnerUUID, Integer count, Integer start) {
        return null;
    }

    @Override
    public BookingDto getBookingDetails(String partnerUUID, String bookingUUID) {
        return null;
    }
}

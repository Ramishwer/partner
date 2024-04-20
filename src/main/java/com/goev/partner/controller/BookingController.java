package com.goev.partner.controller;

import com.goev.lib.dto.ResponseDto;
import com.goev.partner.dto.booking.BookingDto;
import com.goev.partner.dto.common.PaginatedResponseDto;
import com.goev.partner.service.booking.PartnerBookingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/partner-management")
@AllArgsConstructor
public class BookingController {

    private final PartnerBookingService partnerBookingService;

    @GetMapping("/{partner-uuid}/bookings")
    public ResponseDto<PaginatedResponseDto<BookingDto>> getBookings(@PathVariable("partner-uuid")String partnerUUID,
                                                                     @RequestParam("count")Integer count,
                                                                     @RequestParam("start")Integer start){
        return null;
    }


    @GetMapping("/{partner-uuid}/bookings/{booking-uuid}")
    public ResponseDto<BookingDto> getBookingDetails(@PathVariable("partner-uuid")String partnerUUID,
                                                                     @PathVariable("booking-uuid")String bookingUUID){
        return null;
    }
}

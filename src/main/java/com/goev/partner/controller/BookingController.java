package com.goev.partner.controller;

import com.goev.lib.dto.ResponseDto;
import com.goev.lib.dto.StatusDto;
import com.goev.partner.dto.booking.BookingViewDto;
import com.goev.partner.dto.common.PageDto;
import com.goev.partner.dto.common.PaginatedResponseDto;
import com.goev.partner.service.booking.PartnerBookingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/partner-management")
@AllArgsConstructor
public class BookingController {

    private final PartnerBookingService partnerBookingService;

    @GetMapping("/partners/{partner-uuid}/bookings")
    public ResponseDto<PaginatedResponseDto<BookingViewDto>> getBookings(@PathVariable("partner-uuid")String partnerUUID,
                                                                         PageDto page){
        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(),200, partnerBookingService.getBookings(partnerUUID,page));
    }


    @GetMapping("/partners/{partner-uuid}/bookings/{booking-uuid}")
    public ResponseDto<BookingViewDto> getBookingDetails(@PathVariable("partner-uuid")String partnerUUID,
                                                                     @PathVariable("booking-uuid")String bookingUUID){
        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(),200, partnerBookingService.getBookingDetails(partnerUUID,bookingUUID));
    }
}

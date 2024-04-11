package com.goev.partner.controller;

import com.goev.lib.dto.ResponseDto;
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
    public ResponseDto getBookings(@PathVariable("partner-uuid")String partnerUUID,
                                     @RequestParam("from")DateTime from,
                                     @RequestParam("to")DateTime to,
                                     @RequestParam("count")Integer count,
                                     @RequestParam("start")Integer start){
        return null;
    }
}

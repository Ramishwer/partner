package com.goev.partner.service.booking.impl;

import com.goev.lib.exceptions.ResponseException;
import com.goev.partner.constant.ApplicationConstants;
import com.goev.partner.dao.booking.BookingDao;
import com.goev.partner.dao.partner.detail.PartnerDao;
import com.goev.partner.dto.booking.BookingViewDto;
import com.goev.partner.dto.common.PageDto;
import com.goev.partner.dto.common.PaginatedResponseDto;
import com.goev.partner.repository.booking.BookingRepository;
import com.goev.partner.repository.partner.detail.PartnerRepository;
import com.goev.partner.service.booking.PartnerBookingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class PartnerBookingServiceImpl implements PartnerBookingService {

    private final PartnerRepository partnerRepository;
    private final BookingRepository bookingRepository;

    @Override
    public PaginatedResponseDto<BookingViewDto> getBookings(String partnerUUID, PageDto page) {
        PartnerDao partnerDao = partnerRepository.findByUUID(partnerUUID);
        if (partnerDao == null)
            throw new ResponseException("No partner found for id :" + partnerUUID);

        PaginatedResponseDto<BookingViewDto> result = PaginatedResponseDto.<BookingViewDto>builder().elements(new ArrayList<>()).build();
        List<BookingDao> bookings = bookingRepository.findAllByPartnerId(partnerDao.getId(), page);
        if (CollectionUtils.isEmpty(bookings))
            return result;
        for (BookingDao booking : bookings) {
            BookingViewDto bookingViewDto = BookingViewDto.fromDao(booking);
            if (bookingViewDto == null)
                continue;
            bookingViewDto.setUuid(booking.getUuid());
            result.getElements().add(bookingViewDto);
        }
        return result;
    }

    @Override
    public BookingViewDto getBookingDetails(String partnerUUID, String bookingUUID) {
        PartnerDao partnerDao = partnerRepository.findByUUID(partnerUUID);
        if (partnerDao == null)
            throw new ResponseException("No partner found for id :" + partnerUUID);
        BookingDao booking = bookingRepository.findByPartnerIdAndUUID(partnerDao.getId(), bookingUUID);
        if (booking == null)
            throw new ResponseException("No booking found for id :" + bookingUUID);

        BookingViewDto bookingViewDto = BookingViewDto.fromDao(booking);
        if (bookingViewDto == null)
            throw new ResponseException("No booking data found for id :" + bookingUUID);
        bookingViewDto.setUuid(booking.getUuid());

        return bookingViewDto;
    }
}

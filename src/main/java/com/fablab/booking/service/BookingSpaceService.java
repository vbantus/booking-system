package com.fablab.booking.service;

import com.fablab.booking.domain.BookingSpace;
import com.fablab.booking.dto.RqBookingSpaceDto;
import com.fablab.booking.dto.RsBookingSpaceDto;

import java.util.List;

public interface BookingSpaceService {

    List<RsBookingSpaceDto> findAll();

    BookingSpace findById(Long id);

    RsBookingSpaceDto save(RqBookingSpaceDto rqBookingSpaceDto);

    RsBookingSpaceDto update(RqBookingSpaceDto rqBookingSpaceDto, Long id);

    void deleteById(Long id);

}

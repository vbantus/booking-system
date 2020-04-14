package com.fablab.booking.service;

import com.fablab.booking.domain.BookingSpace;
import com.fablab.booking.dto.RqBookingSpaceDto;
import com.fablab.booking.dto.RsBookingSpaceDto;

import java.util.List;

public interface BookingSpaceService {

    RsBookingSpaceDto save(RqBookingSpaceDto rqBookingSpaceDto);

    RsBookingSpaceDto update(RqBookingSpaceDto rqBookingSpaceDto, Long id);

    void deleteById(Long id);

    List<RsBookingSpaceDto> getAll();

    BookingSpace findById(Long id);
}

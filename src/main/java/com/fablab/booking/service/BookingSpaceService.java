package com.fablab.booking.service;

import com.fablab.booking.dto.RqBookingSpaceDto;
import com.fablab.booking.dto.RsBookingSpaceDto;

import java.util.List;

public interface BookingSpaceService {

    List<RsBookingSpaceDto> findAll();

    RsBookingSpaceDto save(RqBookingSpaceDto rqBookingSpaceDto);

    RsBookingSpaceDto update(RqBookingSpaceDto rqBookingSpaceDto, Long id);

    void deleteById(Long id);

}

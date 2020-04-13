package com.fablab.booking.service;

import com.fablab.booking.dto.RqBookingSpaceRelationDto;
import com.fablab.booking.dto.RsBookingSpaceRelationDto;

public interface BookingSpaceRelationService {

    RsBookingSpaceRelationDto save(RqBookingSpaceRelationDto rqBookingSpaceRelationDto);

    RsBookingSpaceRelationDto update(RqBookingSpaceRelationDto rqBookingSpaceRelationDto, Long id);

    void deleteById(Long id);
}

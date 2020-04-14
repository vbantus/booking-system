package com.fablab.booking.service;

import com.fablab.booking.dto.RqBookingSpaceRelationDto;
import com.fablab.booking.dto.RsBookingSpaceRelationDto;

import java.util.List;

public interface BookingSpaceRelationService {

    RsBookingSpaceRelationDto save(RqBookingSpaceRelationDto rqBookingSpaceRelationDto);

    RsBookingSpaceRelationDto update(RqBookingSpaceRelationDto rqBookingSpaceRelationDto, Long id);

    void deleteById(Long id);

    List<RsBookingSpaceRelationDto> getAll();

    List<RsBookingSpaceRelationDto> getAllPendingBookings();

    List<RsBookingSpaceRelationDto> getAllActiveBookings();

    List<RsBookingSpaceRelationDto> getAllExpiredBookings();

    List<RsBookingSpaceRelationDto> getAllPendingBookingsByUserId(Long userId);

    List<RsBookingSpaceRelationDto> getAllActiveBookingsByUserId(Long userId);

    List<RsBookingSpaceRelationDto> getAllExpiredBookingsByUserId(Long userId);
}

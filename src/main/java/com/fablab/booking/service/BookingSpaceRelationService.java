package com.fablab.booking.service;

import com.fablab.booking.dto.RqBookingSpaceRelationDto;
import com.fablab.booking.dto.RsBookingSpaceRelationDto;

import java.util.List;

public interface BookingSpaceRelationService {

    RsBookingSpaceRelationDto save(RqBookingSpaceRelationDto rqBookingSpaceRelationDto);

    RsBookingSpaceRelationDto update(RqBookingSpaceRelationDto rqBookingSpaceRelationDto, Long id);

    void deleteById(Long id);

    List<RsBookingSpaceRelationDto> findAllPendingBookings();

    List<RsBookingSpaceRelationDto> findAllActiveBookings();

    List<RsBookingSpaceRelationDto> findAllExpiredBookings();

    List<RsBookingSpaceRelationDto> findAllPendingBookingsByUserId(Long userId);

    List<RsBookingSpaceRelationDto> findAllActiveBookingsByUserId(Long userId);

    List<RsBookingSpaceRelationDto> findAllExpiredBookingsByUserId(Long userId);

    List<RsBookingSpaceRelationDto> findAll();
}

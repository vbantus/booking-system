package com.fablab.booking.service;

import com.fablab.booking.domain.RoomBooking;
import com.fablab.booking.dto.RqRoomBookingDto;
import com.fablab.booking.dto.RsRoomBookingDto;

import java.util.Date;
import java.util.List;

public interface RoomBookingService {

    RsRoomBookingDto save(RqRoomBookingDto rqRoomBookingDto);

    RsRoomBookingDto update(RqRoomBookingDto rqRoomBookingDto, Long id);

    void deleteById(Long id);

    List<RsRoomBookingDto> getAll();

    List<RsRoomBookingDto> getAllPendingBookings();

    List<RsRoomBookingDto> getAllActiveBookings();

    List<RsRoomBookingDto> getAllExpiredBookings();

    List<RsRoomBookingDto> getAllPendingBookingsByUserId(Long userId);

    List<RsRoomBookingDto> getAllActiveBookingsByUserId(Long userId);

    List<RsRoomBookingDto> getAllExpiredBookingsByUserId(Long userId);

    Long count();

    RoomBooking findById(Long id);

    List<RoomBooking> findAllByCreateDateGreaterThanEqual(Date date);
}

package com.fablab.booking.service.impl;

import com.fablab.booking.domain.RoomBooking;
import com.fablab.booking.domain.common.BookingStatus;
import com.fablab.booking.dto.RqRoomBookingDto;
import com.fablab.booking.dto.RsRoomBookingDto;
import com.fablab.booking.exception.BookingNotAllowedException;
import com.fablab.booking.exception.EntityNotFoundException;
import com.fablab.booking.mapper.RoomBookingMapper;
import com.fablab.booking.repository.RoomBookingRepository;
import com.fablab.booking.service.RoomBookingService;
import com.fablab.booking.service.RoomService;
import com.fablab.booking.service.UserService;
import com.fablab.booking.service.utils.TimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomBookingServiceImpl implements RoomBookingService {

    private final RoomBookingRepository roomBookingRepository;
    private final UserService userService;
    private final RoomService roomService;

    @Override
    public RsRoomBookingDto save(RqRoomBookingDto rqRoomBookingDto) {
        RoomBooking roomBooking =
                RoomBookingMapper.INSTANCE.rqBookingSpaceRelationDtoToBookingSpaceRelation(rqRoomBookingDto);
        TimeUtils.validateDates(roomBooking.getStartBookingTime(), roomBooking.getEndBookingTime());
        checkRoomAvailability(roomBooking, rqRoomBookingDto.getRoomId());

        roomBooking.setStatus(BookingStatus.CREATED);
        roomBooking.setUser(userService.findById(rqRoomBookingDto.getUserId()));
        roomBooking.setRoom(roomService.findById(rqRoomBookingDto.getRoomId()));

        return RoomBookingMapper.INSTANCE
                .roomBookingToRsRoomBookingDto(roomBookingRepository.save(roomBooking));
    }

    @Override
    public RsRoomBookingDto update(RqRoomBookingDto rqRoomBookingDto, Long id) {
        RoomBooking roomBooking = findById(id);
        RoomBookingMapper.INSTANCE
                .updateBookingSpaceRelationFromRqBookingSpaceRelationDto(rqRoomBookingDto, roomBooking);
        TimeUtils.validateDates(roomBooking.getStartBookingTime(), roomBooking.getEndBookingTime());
        checkRoomAvailability(roomBooking, rqRoomBookingDto.getRoomId());

        return RoomBookingMapper.INSTANCE
                .roomBookingToRsRoomBookingDto(roomBookingRepository.save(roomBooking));
    }

    @Override
    public void deleteById(Long id) {
        roomBookingRepository.deleteById(id);
    }

    @Override
    public List<RsRoomBookingDto> getAll() {
        return roomBookingRepository.findAll().stream()
                .map(RoomBookingMapper.INSTANCE::roomBookingToRsRoomBookingDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RsRoomBookingDto> getAllPendingBookings() {
        return roomBookingRepository.findAllPendingBookings().stream()
                .map(RoomBookingMapper.INSTANCE::roomBookingToRsRoomBookingDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RsRoomBookingDto> getAllActiveBookings() {
        return roomBookingRepository.findAllActiveBookings().stream()
                .map(RoomBookingMapper.INSTANCE::roomBookingToRsRoomBookingDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RsRoomBookingDto> getAllExpiredBookings() {
        return roomBookingRepository.findAllExpiredBookings().stream()
                .map(RoomBookingMapper.INSTANCE::roomBookingToRsRoomBookingDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RsRoomBookingDto> getAllPendingBookingsByUserId(Long userId) {
        return roomBookingRepository.findAllPendingBookingsByUserId(userId).stream()
                .map(RoomBookingMapper.INSTANCE::roomBookingToRsRoomBookingDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RsRoomBookingDto> getAllActiveBookingsByUserId(Long userId) {
        return roomBookingRepository.findAllActiveBookingsByUserId(userId).stream()
                .map(RoomBookingMapper.INSTANCE::roomBookingToRsRoomBookingDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RsRoomBookingDto> getAllExpiredBookingsByUserId(Long userId) {
        return roomBookingRepository.findAllExpiredBookingsByUserId(userId).stream()
                .map(RoomBookingMapper.INSTANCE::roomBookingToRsRoomBookingDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoomBooking findById(Long id) {
        return roomBookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("hall booking not found by id: " + id));
    }

    private void checkRoomAvailability(RoomBooking roomBooking, Long roomId) {
        Long count = roomBookingRepository.countAllBookingsInGivenPeriodByRoomId(
                roomBooking.getStartBookingTime(),
                roomBooking.getEndBookingTime(),
                roomId);

        if (count > 0) {
            throw new BookingNotAllowedException("Room is not available for booking in this period of time. " +
                    "There are other " + count + " bookings which conflict with this one.");
        }
    }
}

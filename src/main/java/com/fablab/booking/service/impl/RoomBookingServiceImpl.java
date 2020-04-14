package com.fablab.booking.service.impl;

import com.fablab.booking.domain.RoomBooking;
import com.fablab.booking.domain.common.BookingStatus;
import com.fablab.booking.domain.common.exception.EntityNotFoundException;
import com.fablab.booking.dto.RqBookingSpaceRelationDto;
import com.fablab.booking.dto.RsBookingSpaceRelationDto;
import com.fablab.booking.mapper.RoomBookingMapper;
import com.fablab.booking.repository.RoomBookingRepository;
import com.fablab.booking.service.RoomBookingService;
import com.fablab.booking.service.RoomService;
import com.fablab.booking.service.UserService;
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
    public RsBookingSpaceRelationDto save(RqBookingSpaceRelationDto rqBookingSpaceRelationDto) {
        RoomBooking roomBooking =
                RoomBookingMapper.INSTANCE.rqBookingSpaceRelationDtoToBookingSpaceRelation(rqBookingSpaceRelationDto);

        roomBooking.setStatus(BookingStatus.CREATED);
        roomBooking.setUser(userService.findById(rqBookingSpaceRelationDto.getUserId()));
        roomBooking.setRoom(roomService.findById(rqBookingSpaceRelationDto.getBookingSpaceId()));

        return RoomBookingMapper.INSTANCE
                .bookingSpaceRelationToRsBookingSpaceRelationDto(roomBookingRepository.save(roomBooking));
    }

    @Override
    public RsBookingSpaceRelationDto update(RqBookingSpaceRelationDto rqBookingSpaceRelationDto, Long id) {
        RoomBooking roomBooking = findById(id);
        RoomBookingMapper.INSTANCE
                .updateBookingSpaceRelationFromRqBookingSpaceRelationDto(rqBookingSpaceRelationDto, roomBooking);

        return RoomBookingMapper.INSTANCE
                .bookingSpaceRelationToRsBookingSpaceRelationDto(roomBookingRepository.save(roomBooking));
    }

    @Override
    public void deleteById(Long id) {
        roomBookingRepository.deleteById(id);
    }

    @Override
    public List<RsBookingSpaceRelationDto> getAll() {
        return roomBookingRepository.findAll().stream()
                .map(RoomBookingMapper.INSTANCE::bookingSpaceRelationToRsBookingSpaceRelationDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RsBookingSpaceRelationDto> getAllPendingBookings() {
        return roomBookingRepository.findAllPendingBookings().stream()
                .map(RoomBookingMapper.INSTANCE::bookingSpaceRelationToRsBookingSpaceRelationDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RsBookingSpaceRelationDto> getAllActiveBookings() {
        return roomBookingRepository.findAllActiveBookings().stream()
                .map(RoomBookingMapper.INSTANCE::bookingSpaceRelationToRsBookingSpaceRelationDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RsBookingSpaceRelationDto> getAllExpiredBookings() {
        return roomBookingRepository.findAllExpiredBookings().stream()
                .map(RoomBookingMapper.INSTANCE::bookingSpaceRelationToRsBookingSpaceRelationDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RsBookingSpaceRelationDto> getAllPendingBookingsByUserId(Long userId) {
        return roomBookingRepository.findAllPendingBookingsByUserId(userId).stream()
                .map(RoomBookingMapper.INSTANCE::bookingSpaceRelationToRsBookingSpaceRelationDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RsBookingSpaceRelationDto> getAllActiveBookingsByUserId(Long userId) {
        return roomBookingRepository.findAllActiveBookingsByUserId(userId).stream()
                .map(RoomBookingMapper.INSTANCE::bookingSpaceRelationToRsBookingSpaceRelationDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RsBookingSpaceRelationDto> getAllExpiredBookingsByUserId(Long userId) {
        return roomBookingRepository.findAllExpiredBookingsByUserId(userId).stream()
                .map(RoomBookingMapper.INSTANCE::bookingSpaceRelationToRsBookingSpaceRelationDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoomBooking findById(Long id) {
        return roomBookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("hall booking not found by id: " + id));
    }
}

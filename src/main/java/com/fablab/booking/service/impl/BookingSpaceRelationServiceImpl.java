package com.fablab.booking.service.impl;

import com.fablab.booking.domain.BookingSpaceRelation;
import com.fablab.booking.domain.common.BookingStatus;
import com.fablab.booking.domain.common.exception.EntityNotFoundException;
import com.fablab.booking.dto.RqBookingSpaceRelationDto;
import com.fablab.booking.dto.RsBookingSpaceRelationDto;
import com.fablab.booking.mapper.BookingSpaceRelationMapper;
import com.fablab.booking.repository.BookingSpaceRelationRepository;
import com.fablab.booking.service.BookingSpaceRelationService;
import com.fablab.booking.service.RoomService;
import com.fablab.booking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingSpaceRelationServiceImpl implements BookingSpaceRelationService {

    private final BookingSpaceRelationRepository bookingSpaceRelationRepository;
    private final UserService userService;
    private final RoomService roomService;

    @Override
    public RsBookingSpaceRelationDto save(RqBookingSpaceRelationDto rqBookingSpaceRelationDto) {
        BookingSpaceRelation bookingSpaceRelation =
                BookingSpaceRelationMapper.INSTANCE.rqBookingSpaceRelationDtoToBookingSpaceRelation(rqBookingSpaceRelationDto);

        bookingSpaceRelation.setStatus(BookingStatus.CREATED);
        bookingSpaceRelation.setUser(userService.findById(rqBookingSpaceRelationDto.getUserId()));
        bookingSpaceRelation.setRoom(roomService.findById(rqBookingSpaceRelationDto.getBookingSpaceId()));

        return BookingSpaceRelationMapper.INSTANCE
                .bookingSpaceRelationToRsBookingSpaceRelationDto(bookingSpaceRelationRepository.save(bookingSpaceRelation));
    }

    @Override
    public RsBookingSpaceRelationDto update(RqBookingSpaceRelationDto rqBookingSpaceRelationDto, Long id) {
        BookingSpaceRelation bookingSpaceRelation = findById(id);
        BookingSpaceRelationMapper.INSTANCE
                .updateBookingSpaceRelationFromRqBookingSpaceRelationDto(rqBookingSpaceRelationDto, bookingSpaceRelation);

        return BookingSpaceRelationMapper.INSTANCE
                .bookingSpaceRelationToRsBookingSpaceRelationDto(bookingSpaceRelationRepository.save(bookingSpaceRelation));
    }

    @Override
    public void deleteById(Long id) {
        bookingSpaceRelationRepository.deleteById(id);
    }

    @Override
    public List<RsBookingSpaceRelationDto> getAll() {
        return bookingSpaceRelationRepository.findAll().stream()
                .map(BookingSpaceRelationMapper.INSTANCE::bookingSpaceRelationToRsBookingSpaceRelationDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RsBookingSpaceRelationDto> getAllPendingBookings() {
        return bookingSpaceRelationRepository.findAllPendingBookings().stream()
                .map(BookingSpaceRelationMapper.INSTANCE::bookingSpaceRelationToRsBookingSpaceRelationDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RsBookingSpaceRelationDto> getAllActiveBookings() {
        return bookingSpaceRelationRepository.findAllActiveBookings().stream()
                .map(BookingSpaceRelationMapper.INSTANCE::bookingSpaceRelationToRsBookingSpaceRelationDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RsBookingSpaceRelationDto> getAllExpiredBookings() {
        return bookingSpaceRelationRepository.findAllExpiredBookings().stream()
                .map(BookingSpaceRelationMapper.INSTANCE::bookingSpaceRelationToRsBookingSpaceRelationDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RsBookingSpaceRelationDto> getAllPendingBookingsByUserId(Long userId) {
        return bookingSpaceRelationRepository.findAllPendingBookingsByUserId(userId).stream()
                .map(BookingSpaceRelationMapper.INSTANCE::bookingSpaceRelationToRsBookingSpaceRelationDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RsBookingSpaceRelationDto> getAllActiveBookingsByUserId(Long userId) {
        return bookingSpaceRelationRepository.findAllActiveBookingsByUserId(userId).stream()
                .map(BookingSpaceRelationMapper.INSTANCE::bookingSpaceRelationToRsBookingSpaceRelationDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RsBookingSpaceRelationDto> getAllExpiredBookingsByUserId(Long userId) {
        return bookingSpaceRelationRepository.findAllExpiredBookingsByUserId(userId).stream()
                .map(BookingSpaceRelationMapper.INSTANCE::bookingSpaceRelationToRsBookingSpaceRelationDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookingSpaceRelation findById(Long id) {
        return bookingSpaceRelationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("hall booking not found by id: " + id));
    }
}

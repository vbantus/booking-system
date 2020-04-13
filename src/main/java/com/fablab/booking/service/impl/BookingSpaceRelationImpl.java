package com.fablab.booking.service.impl;

import com.fablab.booking.domain.BookingSpaceRelation;
import com.fablab.booking.domain.util.BookingStatus;
import com.fablab.booking.dto.RqBookingSpaceRelationDto;
import com.fablab.booking.dto.RsBookingSpaceRelationDto;
import com.fablab.booking.mapper.BookingSpaceRelationMapper;
import com.fablab.booking.repository.BookingSpaceRelationRepository;
import com.fablab.booking.service.BookingSpaceRelationService;
import com.fablab.booking.service.BookingSpaceService;
import com.fablab.booking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingSpaceRelationImpl implements BookingSpaceRelationService {

    private final BookingSpaceRelationRepository bookingSpaceRelationRepository;
    private final UserService userService;
    private final BookingSpaceService bookingSpaceService;

    @Override
    public RsBookingSpaceRelationDto save(RqBookingSpaceRelationDto rqBookingSpaceRelationDto) {
        BookingSpaceRelation bookingSpaceRelation =
                BookingSpaceRelationMapper.INSTANCE.rqBookingSpaceRelationDtoToBookingSpaceRelation(rqBookingSpaceRelationDto);

        bookingSpaceRelation.setStatus(BookingStatus.CREATED);
        bookingSpaceRelation.setUser(userService.findById(rqBookingSpaceRelationDto.getUserId()));
        bookingSpaceRelation.setBookingSpace(bookingSpaceService.findById(rqBookingSpaceRelationDto.getBookingSpaceId()));

        return BookingSpaceRelationMapper.INSTANCE
                .bookingSpaceRelationToRsBookingSpaceRelationDto(bookingSpaceRelationRepository.save(bookingSpaceRelation));
    }

    @Override
    public RsBookingSpaceRelationDto update(RqBookingSpaceRelationDto rqBookingSpaceRelationDto, Long id) {
        BookingSpaceRelation bookingSpaceRelation = bookingSpaceRelationRepository.findById(id).get();
        BookingSpaceRelationMapper.INSTANCE
                .updateBookingSpaceRelationFromRqBookingSpaceRelationDto(rqBookingSpaceRelationDto, bookingSpaceRelation);

        return BookingSpaceRelationMapper.INSTANCE
                .bookingSpaceRelationToRsBookingSpaceRelationDto(bookingSpaceRelationRepository.save(bookingSpaceRelation));
    }

    @Override
    public void deleteById(Long id) {
        bookingSpaceRelationRepository.deleteById(id);
    }
}

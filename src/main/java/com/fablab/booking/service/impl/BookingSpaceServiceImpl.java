package com.fablab.booking.service.impl;

import com.fablab.booking.domain.BookingSpace;
import com.fablab.booking.dto.RqBookingSpaceDto;
import com.fablab.booking.dto.RsBookingSpaceDto;
import com.fablab.booking.mapper.BookingSpaceMapper;
import com.fablab.booking.repository.BookingSpaceRepository;
import com.fablab.booking.service.BookingSpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingSpaceServiceImpl implements BookingSpaceService {

    private final BookingSpaceRepository bookingSpaceRepository;

    @Override
    public RsBookingSpaceDto save(RqBookingSpaceDto rqBookingSpaceDto) {
        BookingSpace bookingSpace = BookingSpaceMapper.INSTANCE.rqCreateBookingSpaceDtoToBookingSpace(rqBookingSpaceDto);
        return BookingSpaceMapper.INSTANCE.bookingSpaceToRsBookingSpaceDto(bookingSpaceRepository.save(bookingSpace));
    }

    @Override
    public RsBookingSpaceDto update(RqBookingSpaceDto rqBookingSpaceDto, Long id) {
        BookingSpace bookingSpace = bookingSpaceRepository.findById(id).get();
        BookingSpaceMapper.INSTANCE.updateBookingSpaceFromRqBookingSpaceDto(rqBookingSpaceDto, bookingSpace);
        return BookingSpaceMapper.INSTANCE.bookingSpaceToRsBookingSpaceDto(bookingSpaceRepository.save(bookingSpace));
    }

    @Override
    public void deleteById(Long id) {
        bookingSpaceRepository.deleteById(id);
    }

    @Override
    public List<RsBookingSpaceDto> getAll() {
        return bookingSpaceRepository.findAll().stream()
                .map(BookingSpaceMapper.INSTANCE::bookingSpaceToRsBookingSpaceDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookingSpace findById(Long id) {
        return bookingSpaceRepository.findById(id).get();
    }

}

package com.fablab.booking.mapper;

import com.fablab.booking.domain.BookingSpace;
import com.fablab.booking.dto.RqBookingSpaceDto;
import com.fablab.booking.dto.RsBookingSpaceDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(builder = @Builder(disableBuilder = true))
public interface BookingSpaceMapper {

    BookingSpaceMapper INSTANCE = Mappers.getMapper(BookingSpaceMapper.class);

    BookingSpace rqCreateBookingSpaceDtoToBookingSpace(RqBookingSpaceDto rqBookingSpaceDto);

    RsBookingSpaceDto bookingSpaceToRsBookingSpaceDto(BookingSpace bookingSpace);

    void updateBookingSpaceFromRqBookingSpaceDto(RqBookingSpaceDto rqBookingSpaceDto, @MappingTarget BookingSpace bookingSpace);
}

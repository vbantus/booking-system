package com.fablab.booking.mapper;

import com.fablab.booking.domain.RoomBooking;
import com.fablab.booking.dto.RqBookingSpaceRelationDto;
import com.fablab.booking.dto.RsBookingSpaceRelationDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(builder = @Builder(disableBuilder = true))
public interface RoomBookingMapper {

    RoomBookingMapper INSTANCE = Mappers.getMapper(RoomBookingMapper.class);

    RoomBooking rqBookingSpaceRelationDtoToBookingSpaceRelation(RqBookingSpaceRelationDto rqBookingSpaceRelationDto);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "bookingSpace.id", target = "bookingSpaceId")
    RsBookingSpaceRelationDto bookingSpaceRelationToRsBookingSpaceRelationDto(RoomBooking roomBooking);

    void updateBookingSpaceRelationFromRqBookingSpaceRelationDto(RqBookingSpaceRelationDto rqBookingSpaceRelationDto,
                                                                 @MappingTarget RoomBooking roomBooking);

}

package com.fablab.booking.mapper;

import com.fablab.booking.domain.RoomBooking;
import com.fablab.booking.dto.RqRoomBookingDto;
import com.fablab.booking.dto.RsRoomBookingDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(builder = @Builder(disableBuilder = true))
public interface RoomBookingMapper {

    RoomBookingMapper INSTANCE = Mappers.getMapper(RoomBookingMapper.class);

    RoomBooking rqBookingSpaceRelationDtoToBookingSpaceRelation(RqRoomBookingDto rqRoomBookingDto);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "room.id", target = "roomId")
    RsRoomBookingDto roomBookingToRsRoomBookingDto(RoomBooking roomBooking);

    void updateBookingSpaceRelationFromRqBookingSpaceRelationDto(RqRoomBookingDto rqRoomBookingDto,
                                                                 @MappingTarget RoomBooking roomBooking);

}

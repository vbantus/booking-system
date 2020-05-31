package com.fablab.booking.mapper;

import com.fablab.booking.domain.RoomBooking;
import com.fablab.booking.dto.RqRoomBookingDto;
import com.fablab.booking.dto.RsRoomBookingDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import static com.fablab.booking.service.utils.TimeUtils.DATE_PATTERN;

@Mapper(builder = @Builder(disableBuilder = true))
public interface RoomBookingMapper {

    RoomBookingMapper INSTANCE = Mappers.getMapper(RoomBookingMapper.class);

    @Mapping(source = "startBookingTime", target = "startBookingTime", dateFormat = DATE_PATTERN)
    @Mapping(source = "endBookingTime", target = "endBookingTime", dateFormat = DATE_PATTERN)
    RoomBooking rqBookingSpaceRelationDtoToBookingSpaceRelation(RqRoomBookingDto rqRoomBookingDto);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "room.id", target = "roomId")
    RsRoomBookingDto roomBookingToRsRoomBookingDto(RoomBooking roomBooking);

    @Mapping(source = "startBookingTime", target = "startBookingTime", dateFormat = DATE_PATTERN)
    @Mapping(source = "endBookingTime", target = "endBookingTime", dateFormat = DATE_PATTERN)
    void updateBookingSpaceRelationFromRqBookingSpaceRelationDto(RqRoomBookingDto rqRoomBookingDto,
                                                                 @MappingTarget RoomBooking roomBooking);

}

package com.fablab.booking.mapper;

import com.fablab.booking.domain.Room;
import com.fablab.booking.dto.RqRoomDto;
import com.fablab.booking.dto.RsRoomDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(builder = @Builder(disableBuilder = true))
public interface RoomMapper {

    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    Room rqCreateBookingSpaceDtoToBookingSpace(RqRoomDto rqRoomDto);

    RsRoomDto roomToRsRoomDto(Room room);

    void updateBookingSpaceFromRqBookingSpaceDto(RqRoomDto rqRoomDto, @MappingTarget Room room);
}
